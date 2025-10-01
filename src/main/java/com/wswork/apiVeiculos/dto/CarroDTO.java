package com.wswork.apiVeiculos.dto;

import com.wswork.apiVeiculos.entity.Carro;

// CarroDTO.java
public class CarroDTO {
    private Long id;
    private Long timestamp_cadastro;
    private Long modeloId;
    private Integer ano;
    private String combustivel;
    private Integer num_portas;
    private String cor;
    private String nomeModelo;
    private Double valor;

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.timestamp_cadastro = carro.getTimestamp_cadastro();
        this.modeloId = carro.getModelo().getId();
        this.ano = carro.getAno();
        this.combustivel = carro.getCombustivel();
        this.num_portas = carro.getNum_portas();
        this.cor = carro.getCor();
        this.nomeModelo = carro.getModelo().getNome();
        this.valor = carro.getModelo().getValor_fipe();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestampCadastro() {
        return timestamp_cadastro;
    }

    public void setTimestampCadastro(Long timestampCadastro) {
        this.timestamp_cadastro = timestampCadastro;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Integer getNumPortas() {
        return num_portas;
    }

    public void setNumPortas(Integer numPortas) {
        this.num_portas = numPortas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
