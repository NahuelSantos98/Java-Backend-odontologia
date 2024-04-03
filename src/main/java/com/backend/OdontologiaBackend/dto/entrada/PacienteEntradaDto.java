package com.backend.OdontologiaBackend.dto.entrada;

import com.backend.OdontologiaBackend.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@ApiModel(description = "Estructura de datos de entrada para el paciente")
public class PacienteEntradaDto {
    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    @Size(min = 3, max = 50, message = "El nombre de paciente debe tener hasta 50 caracteres")
    @ApiModelProperty(notes = "Hace referencia al nombre del paciente correspondiente")
    private String nombre;

    @NotNull(message = "El apellido del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    @Size(min = 3, max = 50, message = "El apellido del paciente debe tener hasta 50 caracteres")
    @ApiModelProperty(notes = "Hace referencia al apellido del paciente correspondiente")
    private String apellido;
    @Positive(message = "El dni del paciente no puede ser nulo o menor a cero")
    @Digits(integer = 12, fraction = 0, message = "El dni debe tener como máximo 12 dígitos")
    @ApiModelProperty(notes = "Hace referencia al dni del paciente correspondiente")
    private int dni;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Hace referencia a la fecha de ingreso del paciente correspondiente")
    private LocalDate fechaIngreso;

    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @Valid
    @ApiModelProperty(notes = "Hace referencia al domicilio del paciente correspondiente")
    private DomicilioEntradaDto domicilio;

    public PacienteEntradaDto() {
    }

    public PacienteEntradaDto(String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioEntradaDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}
