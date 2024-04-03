package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.OdontologoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.OdontologoSalidaDto;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import com.backend.OdontologiaBackend.service.IOdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //Para que devuelva en formato JSON
@RequestMapping("/odontologos")
@CrossOrigin(origins = "*")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    //REGISTRAR
    @Operation(summary = "Registro de un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody OdontologoEntradaDto odontologoEntradaDto){
        return new ResponseEntity<>(odontologoService.registrar(odontologoEntradaDto), HttpStatus.CREATED);
    }


    //LISTAR
    @Operation(summary = "Listar todos los odontologos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de odontologos disponible",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologo(){
        return new ResponseEntity<>(odontologoService.listarTodos(), HttpStatus.OK);
    }

    //BUSCAR
    @Operation(summary = "Buscar Odontologo con el id proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontologo encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontologo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarPorId(id), HttpStatus.OK);
    }

    //ACTUALIZAR
    @Operation(summary = "Modificar Odontologo con el id proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "El Odontologo ha sido modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody OdontologoEntradaDto odontologoEntradaDto, @PathVariable Long id){
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologoEntradaDto, id), HttpStatus.ACCEPTED);
    }

    //BORRAR
    @Operation(summary = "Eliminar Odontolodo con el id proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontologo eliminado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("El Odontologo fue borrado", HttpStatus.NO_CONTENT);
    }


}
