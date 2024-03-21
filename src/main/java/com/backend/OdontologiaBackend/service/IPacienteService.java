package com.backend.OdontologiaBackend.service;


import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.entity.Paciente;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);
    List<PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto buscarPacientePorId(Long id);
    void eliminarPaciente (Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente (PacienteEntradaDto pacienteEntradaDto, Long id);
}