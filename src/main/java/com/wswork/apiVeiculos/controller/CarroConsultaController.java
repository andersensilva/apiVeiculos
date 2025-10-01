package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.dto.CarroDTO;
import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.repository.CarroRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carros-detalhes")
@Tag(name = "Carros Detalhados", description = "Traz Descrição, Marca, e Modelo dos veiculos")
public class CarroConsultaController {

    @Autowired
    CarroRepository carroRepository;

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
