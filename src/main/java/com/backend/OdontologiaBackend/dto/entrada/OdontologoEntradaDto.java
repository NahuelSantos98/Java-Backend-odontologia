package com.backend.OdontologiaBackend.dto.entrada;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@ApiModel(description = "Estructura de datos de entrada para el odontologo")
public class OdontologoEntradaDto {

    @NotNull(message = "El numero de matricula no puede ser nulo")
    @Size(min =3 , max = 50, message = "El campo debe tener minimo 10 caracteres y maximo 50")
    @Positive(message = "El numero de matricula debe ser positivo")
    @ApiModelProperty(notes = "Hace referencia al numero de matricula del odontologo correspondiente")
    private int numeroMatricula;

    @NotNull(message = "El nombre del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    @Size(min = 3, max = 50, message = "El nombre del Odontologo debe tener entre 3 y 50 caracteres")
    @ApiModelProperty(notes = "Hace referencia al nombre del odontologo correspondiente")
    private String nombre;

    @NotNull(message = "El apellido del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    @Size(min = 3, max = 50, message = "El apellido del odontólogo debe tener entre 3 y 50 caracteres")
    @ApiModelProperty(notes = "Hace referencia al apellido del odontologo correspondiente")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
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
}
