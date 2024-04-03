package com.backend.OdontologiaBackend.dto.entrada;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "Estructura de datos de entrada para el domicilio del paciente")
public class DomicilioEntradaDto {
    @NotNull(message = "La calle no puede estar nulo")
    @NotBlank(message = "El campo calle no puede estar en blanco")
    @Size(min = 3, max = 100, message = "La localidad debe tener entre 3 y 50 caracteres")
    @ApiModelProperty(notes = "Hace referencia a la calle del domicilio correspondiente")
    private String calle;
    @Positive(message = "El numero no puede ser nulo o menor a cero")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    @ApiModelProperty(notes = "Hace referencia al numero de la calle del domicilio correspondiente")
    private int numero;
    @NotNull(message = "El campo localidad no puede ser nulo")
    @NotBlank(message = "El campo localidad no puede estar en blanco")
    @Size(min = 3, max = 50, message = "La localidad debe tener entre 3 y 50 caracteres")
    @ApiModelProperty(notes = "Hace referencia a la localidad del domicilio correspondiente")
    private String localidad;

    @NotNull(message = "El campo provincia no puede ser nulo")
    @NotBlank(message = "El campo provincia no puede estar en blanco")
    @Size(min = 3,max = 58, message = "La localidad debe tener entre 3 y 58 caracteres")
    @ApiModelProperty(notes = "Hace referencia a la provincia del domicilio correspondiente")
    private String provincia;

    public DomicilioEntradaDto() {
    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
