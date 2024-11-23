package com.SpringBoot_RangoExpress.DTO;

import java.math.BigDecimal;
import java.util.List;

public class PratoDTO {
    private String nome;
    private BigDecimal valor;
    private List<String> ingredientes;

    public PratoDTO(String nome, BigDecimal valor, List<String> ingredientes) {
        this.nome = nome;
        this.valor = valor;
        this.ingredientes = ingredientes;
    }

    // Getters
    public String getNome() { return nome; }
    public BigDecimal getValor() { return valor; }
    public List<String> getIngredientes() { return ingredientes; }
}