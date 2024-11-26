package com.SpringBoot_RangoExpress.Enum;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

// Enum que define os pratos predefinidos
public enum PratoPredefinido {
    EXECUTIVO_FRANGO(
            "Executivo Frango",
            new BigDecimal("18.90"),
            Arrays.asList(
                    Proteina.FRANGO_GRELHADO,
                    Carboidrato.ARROZ_BRANCO,
                    Carboidrato.FEIJAO_CARIOCA,
                    Guarnicao.LEGUMES_VAPOR
            )
    ),
    EXECUTIVO_CARNE(
            "Executivo Carne",
            new BigDecimal("20.90"),
            Arrays.asList(
                    Proteina.CARNE_ASSADA,
                    Carboidrato.ARROZ_BRANCO,
                    Carboidrato.FEIJAO_PRETO,
                    Guarnicao.PURE_BATATA
            )
    ),
    FIT_PEIXE(
            "Fit Peixe",
            new BigDecimal("22.90"),
            Arrays.asList(
                    Proteina.FILE_PEIXE,
                    Carboidrato.ARROZ_INTEGRAL,
                    Guarnicao.LEGUMES_VAPOR,
                    Salada.ALFACE
            )
    ),
    VEGETARIANO(
            "Vegetariano",
            new BigDecimal("16.90"),
            Arrays.asList(
                    Proteina.OMELETE,
                    Carboidrato.ARROZ_INTEGRAL,
                    Guarnicao.LEGUMES_REFOGADOS,
                    Salada.JILO
            )
    );

    private final String nome;
    private final BigDecimal valor;
    private final List<Object> ingredientes;

    PratoPredefinido(String nome, BigDecimal valor, List<Object> ingredientes) {
        this.nome = nome;
        this.valor = valor;
        this.ingredientes = ingredientes;
    }

    public String getNome() { return nome; }
    public BigDecimal getValor() { return valor; }
    public List<Object> getIngredientes() { return ingredientes; }
}
