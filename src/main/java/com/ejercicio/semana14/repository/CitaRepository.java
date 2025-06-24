package com.ejercicio.semana14.repository;

import com.ejercicio.semana14.entity.Cita;
import com.ejercicio.semana14.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByMedicoAndFechaHora(Medico medico, LocalDateTime fechaHora);
}

