package com.wswork.apiVeiculos.entity;

import jakarta.persistence.*;

@Entity
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double valor_fipe;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor_fipe() {
        return valor_fipe;
    }

    public void setValor_fipe(Double valor_fipe) {
        this.valor_fipe = valor_fipe;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}

