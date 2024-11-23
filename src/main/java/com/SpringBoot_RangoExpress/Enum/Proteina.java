package com.SpringBoot_RangoExpress.Enum;

public enum Proteina {
    FRANGO_GRELHADO("Frango Grelhado"),
    FRANGO_EMPANADO("Frango Empanado"),
    CARNE_MOIDA("Carne Moida"),
    OVOS("Ovos"),
    COSTELADA("Costelada"),
    LINGUICA_SUINA("Linguiça Suina"),
    ALMONDEGA("Almondega"),
    CARNE_ASSADA("Carne Assada"),
    FILE_PEIXE("Filé de Peixe"),
    BIFE_ACEBOLADO("Bife Acebolado"),
    OMELETE("Omelete");

    private final String nome;

    Proteina(String nome) {
        this.nome = nome;
    }
    public String getNome() { return nome; }
}