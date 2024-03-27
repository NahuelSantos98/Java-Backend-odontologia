package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import com.backend.OdontologiaBackend.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //LISTAR
   @GetMapping("/listar")
   public ResponseEntity<List<PacienteSalidaDto>> listarPaciente(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
   }

    //BUSCAR
   @GetMapping("/buscar/{id}")
   public ResponseEntity<PacienteSalidaDto> buscarPacienteId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
   }

   //REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarpaciente (@RequestBody @Valid PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    //ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto paciente,@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.modificarPaciente(paciente,id), HttpStatus.ACCEPTED);
    }

    //BORRAR
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
