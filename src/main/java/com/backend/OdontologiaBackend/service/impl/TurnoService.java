package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dao.IDao;
import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;
import com.backend.OdontologiaBackend.entity.Turno;
import com.backend.OdontologiaBackend.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService implements ITurnoService {

    private Logger logger = LoggerFactory.getLogger(TurnoService.class);

    private IDao<Turno> turnoIDao;

    private final ModelMapper modelMapper;

    public TurnoService(IDao<Turno> turnoIDao, ModelMapper modelMapper) {
        this.turnoIDao = turnoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto registrar(TurnoEntradaDto turno) {




        return turnoIDao.registrar(turno);
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {

        return turnoIDao.listarTodos();
    }

    public Boolean pacienteIsValid() {


        return false;
    }

    public Boolean odontologoIsValid() {


        return false;
    }

    private void configureMapping(){


    }



}




