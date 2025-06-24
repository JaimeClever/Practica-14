package com.ejercicio.semana14.mapper;

import com.ejercicio.semana14.dto.CitaResponse;
import com.ejercicio.semana14.entity.Cita;

public class CitaMapper {
    public static CitaResponse toDTO(Cita cita) {
        CitaResponse dto = new CitaResponse();
        dto.id = cita.getId();
        dto.nombrePaciente = cita.getPaciente().getNombre();
        dto.dniPaciente = cita.getPaciente().getDni();
        dto.medicoId = cita.getMedico().getId();
        dto.nombreMedico = cita.getMedico().getNombre();
        dto.fechaHora = cita.getFechaHora();
        dto.estado = cita.getEstado().name();
        return dto;
    }
}
