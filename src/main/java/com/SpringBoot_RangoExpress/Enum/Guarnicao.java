package com.SpringBoot_RangoExpress.Enum;

public enum Guarnicao {
    LEGUMES_VAPOR("Legumes no Vapor"),
    PURE_BATATA("Purê de Batata"),
    CHICA_DOIDA("Chica Doida"),
    FAROFA("Farofa"),
    BANANA_FRITA("Banana Frita"),
    MAIONESE("Maionese"),
    MANDIOCA_FRITA("Mandioca Frita"),
    MACARRAO("Macarrão") ,
    LEGUMES_REFOGADOS("Legumes Refogados"),
    RATATOULE("Ratatoulle"),
    TORRESMO("Torresmo");

    private final String nome;

    Guarnicao(String nome) {
        this.nome = nome;
    }
    public String getNome() { return nome; }
}