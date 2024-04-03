package com.backend.OdontologiaBackend.dto.entrada;

import com.backend.OdontologiaBackend.entity.Odontologo;
import com.backend.OdontologiaBackend.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(description = "Estructura de datos de entrada para el turno")
public class TurnoEntradaDto {

    @NotNull(message = "El Odontologo no puede ser nulo")
    @ApiModelProperty(notes = "Hace referencia al id del odontologo del turno correspondiente")
    private Long odontologoId;

    @NotNull(message = "El Paciente no puede ser nulo")
    @ApiModelProperty(notes = "Hace referencia al id del paciente del turno correspondiente")
    private Long pacienteId;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y la hora del turno")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(notes = "Hace referencia a la fecha y hora del turno correspondiente")
    private LocalDateTime fechaYHora;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(Long pacienteId, Long odontologoId, LocalDateTime fechaYHora) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaYHora = fechaYHora;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontolofoId) {
        this.odontologoId = odontolofoId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
