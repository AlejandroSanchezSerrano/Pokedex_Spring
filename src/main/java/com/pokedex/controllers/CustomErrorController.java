package com.pokedex.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(
        @RequestParam(value = "mensaje", required = false, defaultValue = "Error desconocido") String mensaje,
        Model model) {
        
        System.out.println("Error capturado: " + mensaje); // Debug en consola
        model.addAttribute("mensaje", mensaje);
        
        return "error"; 
    }
}
