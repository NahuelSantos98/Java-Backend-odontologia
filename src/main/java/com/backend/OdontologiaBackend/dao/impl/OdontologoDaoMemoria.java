package com.backend.OdontologiaBackend.dao.impl;

import com.backend.OdontologiaBackend.dao.IDao;
import com.backend.OdontologiaBackend.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private Logger logger = LoggerFactory.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologosRepository;

    public OdontologoDaoMemoria(List<Odontologo> odontologosRepository) {
        this.odontologosRepository = odontologosRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        int id = odontologosRepository.size() + 1;
        Odontologo odontologoGuardado = new Odontologo(id, odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());

        odontologosRepository.add(odontologoGuardado);
        logger.info("Paciente guardado: " + odontologoGuardado);

    return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        if(odontologosRepository.size() == 0){
            logger.error("No hay ningun odontologo listado");
            return null;
        }else {
            return odontologosRepository;
        }
    }

    @Override
    public Odontologo buscarPorId(int id) {
        for (Odontologo odontologo : odontologosRepository) {
            if (odontologo.getId() == id) {
                return odontologo;
            }
        }
        logger.error("No se encontró ningún odontólogo con el ID: " + id);
        return null;
    }
}
