package com.backend.OdontologiaBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {    //Le dice al controlador que hacer cuando se lanza una excepcion que esta definida aca.
    //Como queremos que que los RestControllers se comporten y que queremos que retornen cuando se lanza una excepcion

    @ExceptionHandler({ResourceNotFoundException.class})    //Se pueden agregar mas excepciones que se manejen igual
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResourceNotFound(ResourceNotFoundException resourceNotFoundException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado: "+ resourceNotFoundException.getMessage());
        return mensaje;
    };
    //El mensaje que se lanza es "Recurso no encontrado: + (el mensaje que se le pasa por parametro en el metodo que lo usemos.)"
    //Es un manejo global.


    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarBadRequest(BadRequestException badRequestException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "No existe en la base de datos: "+ badRequestException.getMessage());
        return mensaje;
    };



}
