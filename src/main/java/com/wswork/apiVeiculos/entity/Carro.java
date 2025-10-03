package com.wswork.apiVeiculos.entity;

// Carro.java
import jakarta.persistence.*;

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timestamp_cadastro;
    private Integer ano;
    private String combustivel;
    private Integer num_portas;
    private String cor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp_cadastro() {
        return timestamp_cadastro;
    }

    public void setTimestamp_cadastro(Long timestamp_cadastro) {
        this.timestamp_cadastro = timestamp_cadastro;
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

    public Integer getNum_portas() {
        return num_portas;
    }

    public void setNum_portas(Integer num_portas) {
        this.num_portas = num_portas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}

