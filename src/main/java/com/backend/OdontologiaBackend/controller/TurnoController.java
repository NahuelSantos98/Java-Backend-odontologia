package com.backend.OdontologiaBackend.controller;

import com.backend.OdontologiaBackend.dto.entrada.TurnoEntradaDto;
import com.backend.OdontologiaBackend.dto.salida.TurnoSalidaDto;
import com.backend.OdontologiaBackend.exceptions.BadRequestException;
import com.backend.OdontologiaBackend.exceptions.ResourceNotFoundException;
import com.backend.OdontologiaBackend.service.ITurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @Operation(summary = "Registro de un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turno guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrar( @RequestBody @Valid TurnoEntradaDto turnoEntradaDto) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.registrar(turnoEntradaDto), HttpStatus.CREATED );
    }

    @Operation(summary = "Listar todos los turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de turnos disponible",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTodos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }


    @Operation(summary = "Buscar turno con el id proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "turno encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarTurno(id), HttpStatus.OK);
    }

    @Operation(summary = "Modificar turno con el id proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "El turno ha sido modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto,@PathVariable Long id){
        return new ResponseEntity<>(turnoService.modificarTurno(turnoEntradaDto, id), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Eliminar turno con el id proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turno eliminado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("El turno ha sido eliminado con exito", HttpStatus.NO_CONTENT);
    }



}
