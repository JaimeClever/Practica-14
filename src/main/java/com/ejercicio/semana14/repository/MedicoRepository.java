package com.ejercicio.semana14.repository;

import com.ejercicio.semana14.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
