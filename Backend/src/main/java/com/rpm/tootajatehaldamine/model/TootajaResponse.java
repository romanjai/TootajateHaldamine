package com.rpm.tootajatehaldamine.model;

import java.time.LocalDateTime;



public record TootajaResponse(
        Long id,
        String nimi,
        String email,
        String telefon,
        String elukoht,
        LocalDateTime lisatud,
        LocalDateTime muudetud
) {

    public static TootajaResponse fromEntity(Tootaja entity) {
        return new TootajaResponse(
                entity.getId(),
                entity.getNimi(),
                entity.getEmail(),
                entity.getTelefon(),
                entity.getElukoht(),
                entity.getLisatud(),
                entity.getMuudetud()
        );
    }
}
