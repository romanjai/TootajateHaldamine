package com.rpm.tootajatehaldamine;

import com.rpm.tootajatehaldamine.model.Tootaja;
import com.rpm.tootajatehaldamine.repository.TootajadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TootajadRepositoryTest {

    @Autowired
    private TootajadRepository repository;

    @Test
    void shouldSaveAndFindTootaja() {
        Tootaja tootaja = new Tootaja();
        tootaja.setNimi("Test Kasutaja");
        tootaja.setEmail("test@test.ee");
        tootaja.setLisatud(LocalDateTime.now());

        Tootaja saved = repository.save(tootaja);
        Optional<Tootaja> found = repository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getNimi()).isEqualTo("Test Kasutaja");
    }
}
