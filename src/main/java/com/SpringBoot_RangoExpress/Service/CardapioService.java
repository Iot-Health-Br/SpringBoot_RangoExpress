package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.DTO.PratoDTO;
import com.SpringBoot_RangoExpress.Enum.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Serviço para gerenciar o cardápio
@Service
public class CardapioService {
    public List<PratoDTO> getPratosDoDiaDetalhado() {
        return getPratosDoDiaDetalhado(LocalDate.now().getDayOfWeek());
    }

    public List<PratoDTO> getPratosDoDiaDetalhado(DayOfWeek dia) {
        List<PratoPredefinido> pratosDoDia = CardapioDiario.getPratosDoDia(dia);
        return pratosDoDia.stream()
                .map(this::convertToPratoDTO)
                .collect(Collectors.toList());
    }

    private PratoDTO convertToPratoDTO(PratoPredefinido prato) {
        List<String> ingredientes = prato.getIngredientes().stream()
                .map(ingrediente -> {
                    if (ingrediente instanceof Proteina) {
                        return ((Proteina) ingrediente).getNome();
                    } else if (ingrediente instanceof Carboidrato) {
                        return ((Carboidrato) ingrediente).getNome();
                    } else if (ingrediente instanceof Guarnicao) {
                        return ((Guarnicao) ingrediente).getNome();
                    } else if (ingrediente instanceof Salada) {
                        return ((Salada) ingrediente).getNome();
                    }
                    return ingrediente.toString();
                })
                .collect(Collectors.toList());

        return new PratoDTO(
                prato.getNome(),
                prato.getValor(),
                ingredientes
        );
    }
}
