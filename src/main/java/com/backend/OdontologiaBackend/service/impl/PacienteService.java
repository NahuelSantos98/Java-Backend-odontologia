package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dao.IDao;
import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
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


    private IDao<Paciente> pacienteIDao;
    private final ModelMapper modelMapper;

@Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }


    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        logger.info("PacienteEntrada: {}", JsonPrinter.toString(paciente));

        //Tenemos que pasar el PacienteEntrada, a Paciente, y Paciente a PacienteSalida

        //Se crea el paciente entidad y se le pasa el pacienteEntrada asi lo convierte a Paciente
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class); //Convierte el pacienteEntrada a Paciente

        //Tiene que tener id el Paciente asi que creamos una nueva entidad que con el Idao.registrar() va a darnos el id
        Paciente pacienteEntidadConId = pacienteIDao.registrar(pacienteEntidad);

        //Se mapea al PacienteSalida asi se obtiene lo que la function DEBE retornar y con todos los atributos hechos.
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteEntidadConId, PacienteSalidaDto.class);

        logger.info("Paciente que devolvemos al registrar: {}", pacienteSalidaDto);

        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {

    List<PacienteSalidaDto> pacientesSalidaDto = pacienteIDao.listarTodos()
            .stream()//Vuelve la lista de pacientes a un Stream de pacientes, permite procesar y transformar los elementos de la lista de manera funcional y declarativa
            .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class)) //Itera sobre la List y cada elemento lo pasa a un PacienteSalida
            .toList(); //Los vuelve a listar.

        logger.info("listado de todos los pacientes: {}", pacientesSalidaDto);

        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(int id) {

        //Paciente pase a PacienteSalida. Para eso tiene que pasar por el mapper, YA TIENE el ID.
        Paciente pacienteBuscado = pacienteIDao.buscarPorId(id);    //Se declara un paciente que es el que se busca.
        PacienteSalidaDto pacienteEncontrado = null;   //Se declara afuera por las dudas de que NO lo encuentre.

        if(pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
        }else logger.error("El paciente que usted busca no se ha encontrado.");



        return null;
    }

    private void configureMapping(){
    modelMapper.typeMap(PacienteEntradaDto.class,Paciente.class )
            .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio , Paciente::setDomicilio));
            //Asigna el valor del domicilio del pacienteEntrada a el domicilio de Paciente.

        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper-> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
                //Asigna valor de domicilio del paciente al valor de domicilio de pacienteSalida

    }




}