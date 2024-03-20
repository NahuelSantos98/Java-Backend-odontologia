package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;

import com.backend.OdontologiaBackend.entity.Paciente;
import com.backend.OdontologiaBackend.entity.Turno;
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

    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoSalidaDto registrar(TurnoEntradaDto turno) {
        return null;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {return null;
    }



}




