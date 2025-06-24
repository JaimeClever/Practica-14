package com.ejercicio.semana14.controller;

import com.ejercicio.semana14.dto.CitaRequest;
import com.ejercicio.semana14.dto.CitaResponse;
import com.ejercicio.semana14.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin("*")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/programar")
    public ResponseEntity<CitaResponse> programar(@RequestBody CitaRequest dto) {
        return ResponseEntity.ok(citaService.programarCita(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> actualizar(@PathVariable Long id, @RequestBody CitaRequest dto) {
        return ResponseEntity.ok(citaService.actualizarCita(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CitaResponse>> listar() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerCitaPorId(id));
    }

    @PatchMapping("/{id}/atender")
    public ResponseEntity<Void> marcarComoAtendida(@PathVariable Long id) {
        citaService.marcarComoAtendida(id);
        return ResponseEntity.noContent().build();
    }
}
