package com.ejercicio.semana14.service;

import com.ejercicio.semana14.dto.CitaRequest;
import com.ejercicio.semana14.dto.CitaResponse;
import com.ejercicio.semana14.entity.Cita;
import com.ejercicio.semana14.entity.Medico;
import com.ejercicio.semana14.entity.Paciente;
import com.ejercicio.semana14.mapper.CitaMapper;
import com.ejercicio.semana14.repository.CitaRepository;
import com.ejercicio.semana14.repository.MedicoRepository;
import com.ejercicio.semana14.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    public CitaService(CitaRepository citaRepo, PacienteRepository pacienteRepo, MedicoRepository medicoRepo) {
        this.citaRepo = citaRepo;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
    }

    public CitaResponse programarCita(CitaRequest dto) {
        Paciente paciente = pacienteRepo.findByDni(dto.dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepo.findById(dto.medicoId)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        LocalTime hora = dto.fechaHora.toLocalTime();
        if (hora.isBefore(medico.getHoraInicio()) || hora.isAfter(medico.getHoraFin())) {
            throw new RuntimeException("Hora fuera del horario del médico");
        }

        if (citaRepo.existsByMedicoAndFechaHora(medico, dto.fechaHora)) {
            throw new RuntimeException("Ya existe una cita para este médico en esa hora");
        }

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaHora(dto.fechaHora);

        citaRepo.save(cita);
        return CitaMapper.toDTO(cita);
    }

    public CitaResponse actualizarCita(Long id, CitaRequest dto) {
        Cita cita = citaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        Paciente paciente = pacienteRepo.findByDni(dto.dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepo.findById(dto.medicoId)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        LocalTime hora = dto.fechaHora.toLocalTime();
        if (hora.isBefore(medico.getHoraInicio()) || hora.isAfter(medico.getHoraFin())) {
            throw new RuntimeException("Hora fuera del horario del médico");
        }

        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaHora(dto.fechaHora);

        citaRepo.save(cita);
        return CitaMapper.toDTO(cita);
    }

    public void cancelarCita(Long id) {
        Cita cita = citaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(Cita.EstadoCita.CANCELADA);
        citaRepo.save(cita);
    }

    public List<CitaResponse> listarCitas() {
        return citaRepo.findAll().stream()
                .map(CitaMapper::toDTO)
                .toList();
    }

    public CitaResponse obtenerCitaPorId(Long id) {
        Cita cita = citaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return CitaMapper.toDTO(cita);
    }

    public void marcarComoAtendida(Long id) {
        Cita cita = citaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(Cita.EstadoCita.ATENDIDA);
        citaRepo.save(cita);
    }
}
