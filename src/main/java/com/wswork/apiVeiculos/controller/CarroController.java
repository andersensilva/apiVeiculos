package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.dto.CarroDTO;
import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.repository.CarroRepository;
import com.wswork.apiVeiculos.service.CarroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/carros")
@Tag(name = "Carros", description = "CRUD de carros e operações relacionadas")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> findAll() {
        return ResponseEntity.ok(carroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id) {
        return carroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody Carro carro) {
        return ResponseEntity.ok(carroService.save(carro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody Carro carro) {
        return carroService.findById(id)
                .map(c -> {
                    carro.setId(id);
                    return ResponseEntity.ok(carroService.save(carro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

