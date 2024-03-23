package com.backend.OdontologiaBackend.service.impl;

import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoYDevolverloConId(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123432, "Miguel", "Cervantes" );

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrar(odontologoEntradaDto);

        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals(123432 , odontologoSalidaDto.getNumeroMatricula());
    }



    @Test
    @Order(2)
    void deberiaModificarElNombreDelOdontologoPorElNombreMauricio(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123432, "Mauricio", "Cervantes" );

       OdontologoSalidaDto modificacion = odontologoService.modificarOdontologo(odontologoEntradaDto, 1l);

       assertNotNull(modificacion);
        assertEquals("Mauricio", modificacion.getNombre());

    }

    @Test
    @Order(3)
    void deberiaBuscarYEncontrarElOdontologoConId1(){
        OdontologoSalidaDto odontologoBuscado = odontologoService.buscarPorId(1l);

        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(4)
    void deberiaEliminarseElOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(5)
    void deberiaDevolverUnaListaVaciaDePacientes() {
        List<OdontologoSalidaDto> pacientes = odontologoService.listarTodos();

        assertTrue(pacientes.isEmpty());
    }
}