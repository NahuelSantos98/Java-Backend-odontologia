package com.backend.OdontologiaBackend.service;



import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrar(OdontologoEntradaDto odontologo);
    List<OdontologoSalidaDto> listarTodos();
    OdontologoSalidaDto buscarPorId(Long id);
}
