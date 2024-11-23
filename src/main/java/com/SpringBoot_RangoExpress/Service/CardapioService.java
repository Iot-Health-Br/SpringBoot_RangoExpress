package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.Enum.CardapioDiario;
import com.SpringBoot_RangoExpress.Enum.PratoPredefinido;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

// Serviço para gerenciar o cardápio
@Service
public class CardapioService {

    public List<PratoPredefinido> getPratosDoDia() {
        return CardapioDiario.getPratosDoDia(LocalDate.now().getDayOfWeek());
    }

    public List<PratoPredefinido> getPratosDoDia(DayOfWeek dia) {
        return CardapioDiario.getPratosDoDia(dia);
    }
}
