package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.entity.Marca;
import com.wswork.apiVeiculos.entity.Modelo;
import com.wswork.apiVeiculos.service.MarcaService;
import com.wswork.apiVeiculos.service.ModeloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelo")
@Tag(name = "Modelo", description = "CRUD de modelos e operações relacionadas")
public class ModeloController {

    @Autowired
    ModeloService modeloService;

    @GetMapping
    public ResponseEntity<List<Modelo>> findAll() {
        return ResponseEntity.ok(modeloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> findById(@PathVariable Long id) {
        return modeloService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modelo> create(@RequestBody Modelo modelo) {
        return ResponseEntity.ok(modeloService.save(modelo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> update(@PathVariable Long id, @RequestBody Modelo modelo) {
        return modeloService.findById(id)
                .map(c -> {
                    modelo.setId(id);
                    return ResponseEntity.ok(modeloService.save(modelo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        modeloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
