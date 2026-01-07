package com.rpm.tootajatehaldamine.service;


import com.rpm.tootajatehaldamine.model.OutboxEvent;
import com.rpm.tootajatehaldamine.model.Tootaja;
import com.rpm.tootajatehaldamine.model.TootajaRequest;
import com.rpm.tootajatehaldamine.model.TootajaResponse;
import com.rpm.tootajatehaldamine.repository.OutboxRepository;
import com.rpm.tootajatehaldamine.repository.TootajadRepository;
import com.rpm.tootajatehaldamine.workflow.EmployeeData;
import com.rpm.tootajatehaldamine.workflow.EmployeeOnboardingWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DatabaseService {
    public static final String ONBOARDING_TASK_QUEUE = "EMPLOYEE_ONBOARDING_QUEUE";
    private final TootajadRepository repository;
    private final OutboxRepository outboxRepository;
    private final WorkflowClient workflowClient;

    @Transactional(readOnly = true)
    public Page<TootajaResponse> getTootajad(Pageable pageable) {
        return repository.findAll(pageable).map(TootajaResponse::fromEntity);
    }

    @Transactional
    public void addTootaja(TootajaRequest request) {
        Tootaja tootaja = new Tootaja();
        updateEntityFields(tootaja, request);
        tootaja.setLisatud(LocalDateTime.now());
        Tootaja saved = repository.save(tootaja);

        // Transactional Outbox sündmus
        outboxRepository.save(new OutboxEvent(
                "EMPLOYEE_CREATED",
                saved.getId().toString(),
                "{\"name\":\"" + saved.getNimi() + "\"}"
        ));

        // Temporal Workflow käivitamine pärast DB commit'i
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                EmployeeOnboardingWorkflow workflow = workflowClient.newWorkflowStub(
                        EmployeeOnboardingWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setWorkflowId("onboarding-" + saved.getId())
                                .setTaskQueue(ONBOARDING_TASK_QUEUE)
                                .build()
                );
                WorkflowClient.start(workflow::startOnboarding, new EmployeeData(saved.getEmail(), saved.getNimi()));
            }
        });
    }

    @Transactional
    public void editTootaja(TootajaRequest request) {
        Tootaja tootaja = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Tootaja not found: " + request.id()));
        updateEntityFields(tootaja, request);
        repository.save(tootaja);
    }

    @Transactional
    public void deleteTootaja(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete: " + id);
        }
        repository.deleteById(id);
    }

    private void updateEntityFields(Tootaja entity, TootajaRequest request) {
        entity.setNimi(request.nimi());
        entity.setEmail(request.email());
        entity.setTelefon(request.telefon());
        entity.setElukoht(request.elukoht());
        entity.setMuudetud(LocalDateTime.now());
    }
}