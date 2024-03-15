package com.backend.OdontologiaBackend.dto.entrada;

import com.backend.OdontologiaBackend.entity.Odontologo;
import com.backend.OdontologiaBackend.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @NotNull(message = "El Paciente no puede ser nulo")
    private Long pacienteId;
    @NotNull(message = "El Odontologo no puede ser nulo")
    private Long odontolofoId;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y la hora del turno")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaYHora;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(Long pacienteId, Long odontolofoId, LocalDateTime fechaYHora) {
        this.pacienteId = pacienteId;
        this.odontolofoId = odontolofoId;
        this.fechaYHora = fechaYHora;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontolofoId() {
        return odontolofoId;
    }

    public void setOdontolofoId(Long odontolofoId) {
        this.odontolofoId = odontolofoId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
