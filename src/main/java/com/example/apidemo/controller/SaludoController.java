package com.example.apidemo.controller;


import com.example.apidemo.model.Saludo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SaludoController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("saludo/{name}")
    public Saludo saludar(@PathVariable String name){
        return new Saludo(counter.incrementAndGet(),  name);
    }


    @PostMapping("saludo")
    public ResponseEntity<Saludo> saludar(@RequestBody Saludo saludo){
        Saludo saludoPayload = new Saludo(counter.incrementAndGet(), saludo.getContent());

        return new ResponseEntity<>(saludoPayload, HttpStatus.CREATED);
    }
}

