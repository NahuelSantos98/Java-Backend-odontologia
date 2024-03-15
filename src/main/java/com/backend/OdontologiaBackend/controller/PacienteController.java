package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.PacienteEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.PacienteSalidaDto;
import com.backend.OdontologiaBackend.entity.Paciente;
import com.backend.OdontologiaBackend.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/pacientes")
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
   @GetMapping("/buscar/{id}")        //localhost:8080/pacientes/x
   public ResponseEntity<PacienteSalidaDto> buscarPacienteId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.FOUND);
   }

   //REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarpaciente (@RequestBody @Valid PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    //ACUTALIZAR
    @PutMapping("/actualizar/{id}")     //localhost:8080/actualizar/x
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){  //Va a recibir en el body un cliente entero
        return null; //pacienteService.actualizar(paciente)
    }

    //BORRAR
    @DeleteMapping("/borrar")   //localhost:8080/pacientes/eliminar?id=x
    public ResponseEntity<?> borrarPaciente(@RequestParam Long id){      //Se deja el ? Se deja abierta la posibilidad que se pueda hacer cua;quier cosa.
        //pacienteService.eliminar(id)
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
    }
}
