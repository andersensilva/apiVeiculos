package com.wswork.apiVeiculos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // ignora filtros de segurança
public class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // --------------------------
    // CREATE
    // --------------------------
    @Test
    @Sql(statements = {
            "INSERT INTO marca ( nome_marca) VALUES ('HONDA')",
            "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)"
    })
    void deveCriarCarro() throws Exception {
        String json = """
                    {
                      "ano": 2025,
                      "numPortas": 4,
                      "cor": "Preto",
                      "combustivel": "Flex",
                      "modelo": {
                        "id": 1
                      }
                    }
                """;

        mockMvc.perform(post("/api/carros")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cor").value("Preto"));
    }

    // --------------------------
    // READ
    // --------------------------
    @Test
    @Sql(statements = {
            "INSERT INTO marca (nome_marca) VALUES ('HONDA')",
            "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)",
            "INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id) " +
                    "VALUES (1696539488, 2022, 'FLEX', 4, 'PRATA', 1)"
    })
    void deveListarCarros() throws Exception {
        mockMvc.perform(get("/api/carros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.cor == 'PRATA')]").exists());
    }

    // --------------------------
    // UPDATE
    // --------------------------
    @Test
    @Sql(statements = {
            "INSERT INTO marca ( nome_marca) VALUES ('HONDA')",
            "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)",
            "INSERT INTO carro ( timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id) " +
                    "VALUES ( 1696539488, 2022, 'FLEX', 4, 'PRATA', 1)"
    })
    void deveAtualizarCarro() throws Exception {
        String json = """
                    {
                      "ano": 2025,
                      "numPortas": 4,
                      "cor": "AZUL",
                      "combustivel": "Flex",
                      "modelo": {
                        "id": 1
                      }
                    }
                """;

        mockMvc.perform(put("/api/carros/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cor").value("AZUL"));
    }

    // --------------------------
    // DELETE
    // --------------------------
    @Test
    @Sql(statements = {
            "INSERT INTO marca (nome_marca) VALUES ('HONDA')",
            "INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('CIVIC', 120000, 1)",
            "INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id) " +
                    "VALUES (1696539488, 2022, 'FLEX', 4, 'PRATA', 1)"
    })
    void deveExcluirCarro() throws Exception {
        mockMvc.perform(delete("/api/carros/1"))
                .andExpect(status().isNoContent());
    }
}
