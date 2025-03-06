package com.pokedex.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgument(IllegalArgumentException ex) {
        return new ModelAndView("redirect:/error?mensaje=" + ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElement(NoSuchElementException ex) {
        return new ModelAndView("redirect:/error?mensaje=Recurso no encontrado: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        return new ModelAndView("redirect:/error?mensaje=Ocurrio un error inesperado. Mas detalles --> " + ex.getMessage());
    }
}
