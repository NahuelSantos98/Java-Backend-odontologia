package com.backend.OdontologiaBackend.service.impl;


import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;


import com.backend.OdontologiaBackend.entity.Turno;
import com.backend.OdontologiaBackend.exceptions.BadRequestException;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
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
    public TurnoSalidaDto registrar(TurnoEntradaDto turno) throws BadRequestException {
        TurnoSalidaDto turnoSalidaDto;
        PacienteSalidaDto pacienteValido = pacienteService.buscarPacientePorId(turno.getPacienteId());
        OdontologoSalidaDto odontologoValido = odontologoService.buscarPorId(turno.getOdontologoId());

        if(odontologoValido == null || pacienteValido == null){
            if(pacienteValido == null && odontologoValido == null){
                logger.error("El paciente y el odontologo NO se encuentran el la Base de Datos");
                throw new BadRequestException("El paciente y el odontologo NO se encuentran el la Base de Datos");
            } else if (pacienteValido == null) {
                logger.error("El paciente NO se encuentra en la Base de Datos");
                throw new BadRequestException("El paciente NO se encuentra en la Base de Datos");
            } else {
                logger.error("El odontologo NO se encuentra en la Base de Datos");
                throw new BadRequestException("El odontologo NO se encuentra en la Base de Datos");
            }
        } else {
            Turno turnoNuevo = turnoRepository.save(modelMapper.map(turno, Turno.class));
            turnoSalidaDto = entidadADtoSalida(turnoNuevo, pacienteValido, odontologoValido);
            logger.info("El turno se registro con exito: {}", turnoSalidaDto);
        }

        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnoSalidaDtos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        logger.info("Listado de turnos: {}", turnoSalidaDtos);

        return turnoSalidaDtos;
    }

    @Override
    public TurnoSalidaDto buscarTurno(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            logger.info("El paciente que busca es: {}", turnoEncontrado);
        }else {
            logger.error("El turno que usted busca no se ha encontrado");
        }
        return turnoEncontrado;
    }


    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
       if (turnoRepository.findById(id) != null){
           turnoRepository.deleteById(id);
           logger.warn("El turno con id {} ha sido eliminado correctamente.", id);
       } else {
           throw new ResourceNotFoundException("No existe un turno con id " + id);
       }
    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {

        Turno turnoEntity = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoParaActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoParaActualizar != null){
            turnoParaActualizar = turnoEntity;
            turnoRepository.save(turnoParaActualizar);
            turnoSalidaDto = modelMapper.map(turnoParaActualizar, TurnoSalidaDto.class);
            logger.warn("El turno fue actualizado con exito: {}", turnoSalidaDto);
        }else {
            logger.error("El turno no se encuentra registrado, por lo tanto no es posible atualizarlo.");
        }
        return turnoSalidaDto;
    }

    private TurnoSalidaDto entidadADtoSalida(Turno turno, PacienteSalidaDto pacienteSalidaDto, OdontologoSalidaDto odontologoSalidaDto){
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteSalidaDto(pacienteSalidaDto);
        turnoSalidaDto.setOdontologoSalidaDto(odontologoSalidaDto);
        return turnoSalidaDto;
    }

    //Se usa esto ya que al depender de otros servicios con su propio mapper, nos devuelve la entidad mapeada.
}




