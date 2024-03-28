package com.backend.OdontologiaBackend.service.impl;


import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;
import com.backend.OdontologiaBackend.exceptions.BadRequestException;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurnoYRetornarElId() {

        TurnoSalidaDto turnoSalidaDto = null;
        PacienteEntradaDto pacienteRegistrar = new PacienteEntradaDto();
        OdontologoEntradaDto odontologoRegistrar = new OdontologoEntradaDto();
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto();

        pacienteRegistrar.setNombre("Miguel");
        odontologoRegistrar.setNombre("Rodolfo");

        PacienteSalidaDto pacienteSalidaDto= pacienteService.registrarPaciente(pacienteRegistrar);
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrar(odontologoRegistrar);

        turnoEntradaDto.setPacienteId(pacienteSalidaDto.getId());
        turnoEntradaDto.setOdontologoId(odontologoSalidaDto.getId());

        try {
            turnoSalidaDto = turnoService.registrar(turnoEntradaDto);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(turnoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaBorrarElTurnoConElId1() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L,1L, LocalDateTime.of (2025,2,12, 9,30 ));

        try{
            turnoService.eliminarTurno(1L);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertNull(turnoService.buscarTurno(1L));
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaVacia(){
        List<TurnoSalidaDto> turnoSalidaDtos = turnoService.listarTurnos();
        assertTrue(turnoSalidaDtos.isEmpty());
    }


}