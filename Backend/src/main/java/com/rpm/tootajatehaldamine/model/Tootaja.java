package com.rpm.tootajatehaldamine.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tootajad")
@Data
public class Tootaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nimi;

    @Column(nullable = false, unique = true)
    private String email;
    private String telefon;
    private String elukoht;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime lisatud;

    @UpdateTimestamp
    private LocalDateTime muudetud;
}
