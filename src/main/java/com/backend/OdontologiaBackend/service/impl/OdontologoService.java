package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.entity.Odontologo;
import com.backend.OdontologiaBackend.entity.Paciente;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import com.backend.OdontologiaBackend.repository.OdontologoRepository;
import com.backend.OdontologiaBackend.service.IOdontologoService;
import com.backend.OdontologiaBackend.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    private OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrar(OdontologoEntradaDto odontologo) {

        Odontologo odontologoEntity = modelMapper.map(odontologo, Odontologo.class);

        Odontologo odontologoConId = odontologoRepository.save(odontologoEntity);

        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoConId, OdontologoSalidaDto.class);

        logger.info("Odontologo registrado: {}", JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarTodos() {

        List<OdontologoSalidaDto> odontologosSalidaDto = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();

        logger.info("Lista de odontologo: {}", JsonPrinter.toString(odontologosSalidaDto));

        return odontologosSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null) {
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
        } else logger.error("No se pudo encontrar el Odontologo que buscaste.");

        return odontologoEncontrado;
    }


    public void eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        if (odontologoRepository.findById(id) != null) {
            odontologoRepository.deleteById(id);
            logger.warn("El odontologo con id {} ha sido eliminado correctamente.", id);
        } else {
            throw new ResourceNotFoundException("No existe un odontologo con id " + id);
        }
    }

    @Override
    public OdontologoSalidaDto modificarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) {
        Odontologo odontologoEntity = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        Odontologo odontologoParaActualizar = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoParaActualizar != null) {
            odontologoParaActualizar.setNumeroMatricula(odontologoEntity.getNumeroMatricula());
            odontologoParaActualizar.setNombre(odontologoEntity.getNombre());
            odontologoParaActualizar.setApellido(odontologoEntity.getApellido());
            odontologoRepository.save(odontologoParaActualizar);

            odontologoSalidaDto = modelMapper.map(odontologoParaActualizar, OdontologoSalidaDto.class);

            logger.warn("El odontologo fue actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));
        } else {
            logger.error("El odontologo no se encuentra registrado, por lo tanto no es posible atualizarlo.");
        }

        return odontologoSalidaDto;

    }

}
