package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //Para que devuelva en formato JSON
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //BUSCAR
    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarPorId(id), HttpStatus.OK);
    }

    //REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.registrar(odontologo), HttpStatus.CREATED);
    }

    //ACTUALIZAR
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody OdontologoEntradaDto odontologo){
        return null; //odontologoService.actualizar(odontologo)
    }

    //BORRAR
    @DeleteMapping("/borrar")
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id){
        //odontologoService.borrar(id)
        return new ResponseEntity<>("El Odontologo fue borrado", HttpStatus.OK);
    }

    //LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologo(){
        return new ResponseEntity<>(odontologoService.listarTodos(), HttpStatus.OK);
    }
}
