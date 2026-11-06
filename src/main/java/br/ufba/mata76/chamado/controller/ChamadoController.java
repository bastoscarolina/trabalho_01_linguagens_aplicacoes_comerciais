package br.ufba.mata76.chamado.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChamadoController {
    @GetMapping("/")
    public String hello() {
    return "Minha primeira API Spring Boot!";
    }
};