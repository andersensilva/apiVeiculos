package com.wswork.apiVeiculos.controller;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class MarcaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarMarca() throws Exception {
        String json = "{ \"nome_marca\": \"Honda\" }";

        mockMvc.perform(post("/api/marca")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome_marca").value("Honda"));
    }

    @Test
    void deveListarMarcas() throws Exception {
        // Criar uma marca antes de listar
        String json = "{ \"nome_marca\": \"Toyota\" }";
        mockMvc.perform(post("/api/marca")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/marca"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nome_marca", Matchers.hasItem("Toyota")));
    }

    @Test
    void deveAtualizarMarca() throws Exception {
        // Criar marca antes de atualizar
        String jsonCriar = "{ \"nome_marca\": \"Ford\" }";
        MvcResult result = mockMvc.perform(post("/api/marca")
                        .contentType("application/json")
                        .content(jsonCriar))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Integer idTest = JsonPath.read(content, "$.id");
        Long id = idTest.longValue();

        // Atualizar a marca criada
        String jsonAtualizar = "{ \"nome_marca\": \"Ford Atualizada\" }";
        mockMvc.perform(put("/api/marca/" + id)
                        .contentType("application/json")
                        .content(jsonAtualizar))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome_marca").value("Ford Atualizada"));
    }

    @Test
    void deveExcluirMarca() throws Exception {
        // Criar marca antes de excluir
        String jsonCriar = "{ \"nome_marca\": \"Chevrolet\" }";
        MvcResult result = mockMvc.perform(post("/api/marca")
                        .contentType("application/json")
                        .content(jsonCriar))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Integer idTest = JsonPath.read(content, "$.id");
        Long id = idTest.longValue();

        // Excluir a marca criada
        mockMvc.perform(delete("/api/marca/" + id))
                .andExpect(status().isNoContent());
    }
}
