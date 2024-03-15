package com.backend.OdontologiaBackend.repository;

import com.backend.OdontologiaBackend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository No es necesario porque trabajamos con JpaRepository por lo tanto ya lo reconoce como Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {     //Se trabaja desde aca el Domicilio
                                        //Pide un Type (Entity) y el tipo de dato del ID.
}
