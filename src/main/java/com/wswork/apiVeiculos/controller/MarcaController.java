package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.entity.Marca;
import com.wswork.apiVeiculos.service.MarcaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/marca")
@Tag(name = "Marcas", description = "CRUD de marcas e operações relacionadas")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> findAll() {
        return ResponseEntity.ok(marcaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable Long id) {
        return marcaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/check")
    public Map<String, Object> checkVinculos(@PathVariable Long id) {
        long count = marcaService.countModelosVinculados(id);
        Map<String, Object> response = new HashMap<>();
        response.put("modelosVinculados", count);
        return response;
    }

    @PostMapping
    public ResponseEntity<Marca> create(@RequestBody Marca marca) {
        Marca salva = marcaService.save(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> update(@PathVariable Long id, @RequestBody Marca marca) {
        return marcaService.findById(id)
                .map(c -> {
                    marca.setId(id);
                    return ResponseEntity.ok(marcaService.save(marca));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean cascade) {
        marcaService.delete(id, cascade);
        return ResponseEntity.noContent().build();
    }
}
