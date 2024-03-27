package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.entity.Odontologo;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import com.backend.OdontologiaBackend.repository.PacienteRepository;
import com.backend.OdontologiaBackend.service.IPacienteService;
import com.backend.OdontologiaBackend.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.OdontologiaBackend.entity.Paciente;
import org.slf4j.Logger;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    //@Autowired No hace falta porque Spring ya lo reconoce
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        logger.info("PacienteEntrada: {}", JsonPrinter.toString(paciente));

        //Tenemos que pasar el PacienteEntrada, a Paciente, y Paciente a PacienteSalida

        //Se crea el paciente entidad y se le pasa el pacienteEntrada asi lo convierte a Paciente
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class); //Convierte el pacienteEntrada a Paciente

        //Tiene que tener id el Paciente asi que creamos una nueva entidad que con el .save() y va a darnos el id. Es el registrar.
        Paciente pacienteEntidadConId = pacienteRepository.save(pacienteEntidad);

        //Se mapea al PacienteSalida asi se obtiene lo que la function DEBE retornar y con todos los atributos hechos.
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteEntidadConId, PacienteSalidaDto.class);

        logger.info("Paciente que devolvemos al registrar: {}",  JsonPrinter.toString(pacienteSalidaDto));

        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {

    List<PacienteSalidaDto> pacientesSalidaDto = pacienteRepository.findAll()
            .stream()//Vuelve la lista de pacientes a un Stream de pacientes, permite procesar y transformar los elementos de la lista de manera funcional y declarativa
            .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class)) //Itera sobre la List y cada elemento lo pasa a un PacienteSalida
            .toList(); //Los vuelve a listar.

        logger.info("listado de todos los pacientes: {}",  JsonPrinter.toString(pacientesSalidaDto));

        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {


        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteEncontrado = null;

        if(pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
        }else logger.error("El paciente que usted busca no se ha encontrado.");

        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {

        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            logger.warn("Se ha eliminado el paciente con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) {
        Paciente pacienteEntity = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteParaActualizar = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;


            if (pacienteParaActualizar != null) {
                pacienteParaActualizar.setNombre(pacienteEntity.getNombre());
                pacienteParaActualizar.setApellido(pacienteEntity.getApellido());
                pacienteParaActualizar.setDni(pacienteEntity.getDni());
                pacienteParaActualizar.setFechaIngreso(pacienteEntity.getFechaIngreso());
                pacienteParaActualizar.setDomicilio(pacienteEntity.getDomicilio());
                pacienteRepository.save(pacienteParaActualizar);

                pacienteSalidaDto = modelMapper.map(pacienteParaActualizar, PacienteSalidaDto.class);

                logger.warn("El paciente fue actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));
            } else {
                logger.error("El paciente no se encuentra registrado, por lo tanto no es posible atualizarlo.");
            }

            return pacienteSalidaDto;

        }


    private void configureMapping(){
    modelMapper.typeMap(PacienteEntradaDto.class,Paciente.class )
            .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio , Paciente::setDomicilio));


        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper-> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));

    }




}