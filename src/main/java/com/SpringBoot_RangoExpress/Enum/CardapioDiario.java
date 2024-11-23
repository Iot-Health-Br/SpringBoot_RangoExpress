package com.SpringBoot_RangoExpress.Enum;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CardapioDiario {
    SEGUNDA(DayOfWeek.MONDAY, Arrays.asList(
            PratoPredefinido.EXECUTIVO_FRANGO,
            PratoPredefinido.VEGETARIANO
    )),

    TERCA(DayOfWeek.TUESDAY, Arrays.asList(
            PratoPredefinido.EXECUTIVO_CARNE,
            PratoPredefinido.FIT_PEIXE
    )),

    QUARTA(DayOfWeek.WEDNESDAY, Arrays.asList(
            PratoPredefinido.FIT_PEIXE,
            PratoPredefinido.VEGETARIANO
    )),

    QUINTA(DayOfWeek.THURSDAY, Arrays.asList(
            PratoPredefinido.EXECUTIVO_FRANGO,
            PratoPredefinido.EXECUTIVO_CARNE
    )),

    SEXTA(DayOfWeek.FRIDAY, Arrays.asList(
            PratoPredefinido.EXECUTIVO_CARNE,
            PratoPredefinido.FIT_PEIXE
    ));

    private final DayOfWeek dia;
    private final List<PratoPredefinido> pratos;

    CardapioDiario(DayOfWeek dia, List<PratoPredefinido> pratos) {
        this.dia = dia;
        this.pratos = pratos;
    }

    public DayOfWeek getDia() { return dia; }
    public List<PratoPredefinido> getPratos() { return pratos; }

    public static List<PratoPredefinido> getPratosDoDia(DayOfWeek dia) {
        return Arrays.stream(values())
                .filter(cardapio -> cardapio.getDia() == dia)
                .findFirst()
                .map(CardapioDiario::getPratos)
                .orElse(Collections.emptyList());
    }
}