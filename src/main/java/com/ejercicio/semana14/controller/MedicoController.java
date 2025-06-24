package com.ejercicio.semana14.controller;

import com.ejercicio.semana14.entity.Medico;
import com.ejercicio.semana14.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // Crear médico
    @PostMapping
    public ResponseEntity<Medico> crearMedico(@RequestBody Medico medico) {
        return ResponseEntity.ok(medicoService.crearMedico(medico));
    }

    // Listar todos los médicos
    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(medicoService.listarMedicos());
    }

    // Buscar médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Long id) {
        return medicoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
