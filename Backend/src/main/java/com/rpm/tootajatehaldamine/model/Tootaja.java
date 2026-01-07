package com.rpm.tootajatehaldamine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tootajad")
@Data
public class Tootaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimi;
    private String email;
    private String telefon;
    private String elukoht;
    private LocalDateTime lisatud;
    private LocalDateTime muudetud;
}
