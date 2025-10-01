package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.dto.CarroDTO;
import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.repository.CarroRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping()
    public Map<String, Object> listarCarros() {
        List<Carro> carros = carroRepository.findAll();
        List<CarroDTO> carrosDTO = carros.stream()
                .map(CarroDTO::new)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("cars", carrosDTO);
        return response;
    }
}

