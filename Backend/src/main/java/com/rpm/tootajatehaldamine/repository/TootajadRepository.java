package com.rpm.tootajatehaldamine.repository;

import com.rpm.tootajatehaldamine.model.Tootaja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TootajadRepository extends JpaRepository<Tootaja, Long> {
}
