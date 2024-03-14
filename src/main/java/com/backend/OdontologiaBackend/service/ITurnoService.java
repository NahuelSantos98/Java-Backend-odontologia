package com.backend.OdontologiaBackend.service;

import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrar(TurnoEntradaDto turno);
    List<TurnoSalidaDto> listarTurnos();

}
