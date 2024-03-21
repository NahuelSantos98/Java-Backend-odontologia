package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;
import com.backend.OdontologiaBackend.exceptions.BadRequestException;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import com.backend.OdontologiaBackend.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTodos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrar(TurnoEntradaDto turno) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.registrar(turno), HttpStatus.CREATED );
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarTurno(id), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("El turno ha sido eliminado con exito", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto,@PathVariable Long id){
      return new ResponseEntity<>(turnoService.modificarTurno(turnoEntradaDto, id), HttpStatus.ACCEPTED);
    }

}
