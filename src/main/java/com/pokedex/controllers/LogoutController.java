package com.pokedex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout";
    }
}
