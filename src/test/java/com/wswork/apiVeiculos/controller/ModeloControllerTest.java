package com.wswork.apiVeiculos.controller;

import com.wswork.apiVeiculos.entity.Carro;
import com.wswork.apiVeiculos.entity.Modelo;
import com.wswork.apiVeiculos.repository.CarroRepository;
import com.wswork.apiVeiculos.repository.ModeloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ModeloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private CarroRepository carroRepository;


    @BeforeEach
    void setup() {
        // Limpa carros e modelos antes de cada teste
        carroRepository.deleteAll();
        modeloRepository.deleteAll();
    }

    @Test
    void deveCriarModelo() throws Exception {
        String json = "{ \"nome\": \"Civic\", \"marcaId\": 1 }";

        mockMvc.perform(post("/api/modelo")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Civic"));
    }

    @Test
    void deveListarModelos() throws Exception {
        mockMvc.perform(get("/api/modelo"))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarModelo() throws Exception {
        // Primeiro cria o modelo
        Modelo modelo = new Modelo();
        modelo.setNome("Civic");
        modeloRepository.save(modelo);

        String json = "{ \"nome\": \"Civic Touring\", \"marcaId\": 1 }";

        mockMvc.perform(put("/api/modelo/" + modelo.getId())
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Civic Touring"));
    }

    @Test
    void deveExcluirModelo() throws Exception {
        // Cria um modelo sem carros vinculados
        Modelo modelo = new Modelo();
        modelo.setNome("Gol");
        modeloRepository.save(modelo);

        mockMvc.perform(delete("/api/modelo/" + modelo.getId()))
                .andExpect(status().isNoContent());
    }
}
