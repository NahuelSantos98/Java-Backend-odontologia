package com.backend.OdontologiaBackend.service;



import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrar(OdontologoEntradaDto odontologo);
    List<OdontologoSalidaDto> listarTodos();
    OdontologoSalidaDto buscarPorId(Long id);
    void eliminarOdontologo (Long id) throws ResourceNotFoundException;
    OdontologoSalidaDto modificarOdontologo (OdontologoEntradaDto odontologoEntradaDto, Long id);
}
