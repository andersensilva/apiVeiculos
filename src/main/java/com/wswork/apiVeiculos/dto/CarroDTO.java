package com.wswork.apiVeiculos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wswork.apiVeiculos.entity.Carro;

import java.io.IOException;
import java.text.DecimalFormat;

@JsonPropertyOrder({
        "id",
        "timestamp_cadastro",
        "modelo_id",
        "ano",
        "combustivel",
        "num_portas",
        "cor",
        "nome_modelo",
        "valor"
})
public class CarroDTO {

    private Long id;

    @JsonProperty("timestamp_cadastro")
    private Long timestampCadastro;

    @JsonProperty("modelo_id")
    private Long modeloId;

    private Integer ano;
    private String combustivel;

    @JsonProperty("num_portas")
    private Integer numPortas;

    private String cor;

    @JsonProperty("nome_modelo")
    private String nomeModelo;

    @JsonSerialize(using = ValorSerializer.class)
    private Double valor;

    // Construtor
    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.timestampCadastro = carro.getTimestamp_cadastro();
        this.modeloId = carro.getModelo().getId();
        this.ano = carro.getAno();
        this.combustivel = carro.getCombustivel();
        this.numPortas = carro.getNum_portas();
        this.cor = carro.getCor();
        this.nomeModelo = carro.getModelo().getNome();
        this.valor = carro.getModelo().getValor_fipe();
    }

    // Getters e setters (use o mesmo nome que o campo Java)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTimestampCadastro() { return timestampCadastro; }
    public void setTimestampCadastro(Long timestampCadastro) { this.timestampCadastro = timestampCadastro; }

    public Long getModeloId() { return modeloId; }
    public void setModeloId(Long modeloId) { this.modeloId = modeloId; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }

    public Integer getNumPortas() { return numPortas; }
    public void setNumPortas(Integer numPortas) { this.numPortas = numPortas; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getNomeModelo() { return nomeModelo; }
    public void setNomeModelo(String nomeModelo) { this.nomeModelo = nomeModelo; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    // Serializer
    public static class ValorSerializer extends JsonSerializer<Double> {
        private static final DecimalFormat df = new DecimalFormat("#,###");

        @Override
        public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(df.format(value));
        }
    }
}
