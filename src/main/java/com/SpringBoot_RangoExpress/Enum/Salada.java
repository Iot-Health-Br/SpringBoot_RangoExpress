package com.SpringBoot_RangoExpress.Enum;

public enum Salada {
    VINAGRETE("Vinagrete"),
    JILO("Jiló"),
    ALFACE("Alface"),
    RUCULA("Rucula"),
    BROCOLIS("Brocolis"),
    ACELGA("Acelga"),
    TOMATE("Tomate"),
    COUVE("Couve");

    private final String nome;

    Salada(String nome) {
        this.nome = nome;
    }
    public String getNome() { return nome; }

}
