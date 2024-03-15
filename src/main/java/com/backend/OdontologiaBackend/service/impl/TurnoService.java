package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;

import com.backend.OdontologiaBackend.repository.TurnoRepository;
import com.backend.OdontologiaBackend.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService implements ITurnoService {

    private Logger logger = LoggerFactory.getLogger(TurnoService.class);

    private TurnoRepository turnoRepository;

    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto registrar(TurnoEntradaDto turno) {
        return null;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        return null;
    }
}




