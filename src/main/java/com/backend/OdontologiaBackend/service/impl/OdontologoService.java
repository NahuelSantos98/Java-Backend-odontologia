package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.entity.Odontologo;
import com.backend.OdontologiaBackend.repository.OdontologoRepository;
import com.backend.OdontologiaBackend.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    //@Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrar(OdontologoEntradaDto odontologo) {

            Odontologo odontologoEntity = modelMapper.map(odontologo, Odontologo.class);

            Odontologo odontologoConId = odontologoRepository.save(odontologoEntity);

            OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoConId, OdontologoSalidaDto.class);

            logger.info("Odontologo registrado: {}", odontologoSalidaDto);

            return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarTodos() {

        List<OdontologoSalidaDto> odontologosSalidaDto = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();

        logger.info("Lista de odontologo: {}", odontologosSalidaDto);

        return odontologosSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoEncontrado = null;

        if( odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
        }else logger.error("No se pudo encontrar el Odontologo que buscaste.");

        return odontologoEncontrado;
    }
}
