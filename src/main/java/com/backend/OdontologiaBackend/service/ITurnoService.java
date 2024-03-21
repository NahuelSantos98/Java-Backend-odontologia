package com.backend.OdontologiaBackend.service;

import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;
import com.backend.OdontologiaBackend.exceptions.BadRequestException;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrar(TurnoEntradaDto turno) throws BadRequestException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurno (Long id);
    void eliminarTurno (Long id) throws ResourceNotFoundException;
    TurnoSalidaDto modificarTurno (TurnoEntradaDto turnoEntradaDto, Long id);

}
