package com.backend.OdontologiaBackend.dto.entrada;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @NotNull(message = "El numero de matricula no puede ser nulo")
    @Size(max = 50, message = "El campo debe tener minimo 10 caracteres y maximo 50")
    @Positive(message = "No puede ser nulo")
    private int numeroMatricula;

    @NotNull(message = "El nombre de odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    @Size(min = 2, max = 50, message = "El nombre del odontólogo debe tener hasta 50 caracteres")
    private String nombre;

    @NotNull(message = "El apellido de odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    @Size(min = 2, max = 50, message = "El apellido de odontólogo debe tener hasta 50 caracteres")
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
