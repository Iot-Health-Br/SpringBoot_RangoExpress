package com.SpringBoot_RangoExpress.Enum;

public enum LojaLocalização {
    NOME("Rango Express"),
    CNPJ("77.741.896/0001-20"),
    TELEFONE("(62) 9 9251-4569"),
    ENDERECO("Av. Portugal, Nº1148, Órion Business - St. Marista, CEP:74150-030"),
    LATITUDE("-16.696775"),
    LONGITUDE("-49.269386");

    private final String valor;

    LojaLocalização(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static double getLatitude() {
        return Double.parseDouble(LATITUDE.valor);
    }

    public static double getLongitude() {
        return Double.parseDouble(LONGITUDE.valor);
    }
}