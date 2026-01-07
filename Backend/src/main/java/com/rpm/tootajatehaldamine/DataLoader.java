package com.rpm.tootajatehaldamine;

import com.rpm.tootajatehaldamine.model.Tootaja;
import com.rpm.tootajatehaldamine.repository.TootajadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TootajadRepository repository;

    @Override
    public void run(String... args) {
        int numberOfFields = 102;
        LocalDateTime now = LocalDateTime.now();

        var tootajad = IntStream.range(0, numberOfFields)
                .mapToObj(i -> {
                    Tootaja tootaja = new Tootaja();
                    tootaja.setNimi("Nimi Perenimi");
                    tootaja.setEmail("email%d@gmail.com".formatted(i + 1)); // Java 15+ .formatted()
                    tootaja.setTelefon("+37255667788");
                    tootaja.setElukoht("Jahu %d, Tallinn".formatted(i + 1));
                    tootaja.setLisatud(now);
                    tootaja.setMuudetud(now);
                    return tootaja;
                })
                .toList(); // Java 16+ .toList()

        repository.saveAll(tootajad);
    }
}