package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;
import com.backend.OdontologiaBackend.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private ITurnoService IturnoService;

    public TurnoController(ITurnoService IturnoService) {
        this.IturnoService = IturnoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTodos(){
        return new ResponseEntity<>(IturnoService.listarTurnos(), HttpStatus.CREATED);
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrar(TurnoEntradaDto turno){
        return new ResponseEntity<>(IturnoService.registrar(turno), HttpStatus.OK );
    }
}
