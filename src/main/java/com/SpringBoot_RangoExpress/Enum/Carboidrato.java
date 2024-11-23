package com.SpringBoot_RangoExpress.Enum;

import java.math.BigDecimal;

public enum Carboidrato {
    ARROZ_BRANCO("Arroz Branco"),
    ARROZ_INTEGRAL("Arroz Integral"),
    FEIJAO_CARIOCA("Feijão Carioca"),
    FEIJAO_PRETO("Feijão Preto");

    private final String nome;

    Carboidrato(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
}