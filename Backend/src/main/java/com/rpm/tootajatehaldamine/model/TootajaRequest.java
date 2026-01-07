package com.rpm.tootajatehaldamine.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TootajaRequest(
        Long id,
        @NotBlank String nimi,
        @Email String email,
        String telefon,
        String elukoht
) {}
