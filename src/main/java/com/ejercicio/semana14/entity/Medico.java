package com.ejercicio.semana14.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especialidad;

    // Horario de atención (simplificado)
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
