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
        // Obtém os nomes dos ingredientes
        List<String> ingredientes = new ArrayList<>();

        // Adiciona os ingredientes baseado no enum PratoPredefinido
        switch (prato) {
            case EXECUTIVO_FRANGO:
                ingredientes.add(Proteina.FRANGO_GRELHADO.getNome());
                ingredientes.add(Carboidrato.ARROZ_BRANCO.getNome());
                ingredientes.add(Carboidrato.FEIJAO_CARIOCA.getNome());
                ingredientes.add(Guarnicao.LEGUMES_VAPOR.getNome());
                break;
            case EXECUTIVO_CARNE:
                ingredientes.add(Proteina.CARNE_ASSADA.getNome());
                ingredientes.add(Carboidrato.ARROZ_BRANCO.getNome());
                ingredientes.add(Carboidrato.FEIJAO_PRETO.getNome());
                ingredientes.add(Guarnicao.PURE_BATATA.getNome());
                break;
            case FIT_PEIXE:
                ingredientes.add(Proteina.FILE_PEIXE.getNome());
                ingredientes.add(Carboidrato.ARROZ_INTEGRAL.getNome());
                ingredientes.add(Guarnicao.LEGUMES_VAPOR.getNome());
                ingredientes.add(Salada.ALFACE.getNome());
                break;
            case VEGETARIANO:
                ingredientes.add(Proteina.OMELETE.getNome());
                ingredientes.add(Carboidrato.ARROZ_INTEGRAL.getNome());
                ingredientes.add(Guarnicao.LEGUMES_REFOGADOS.getNome());
                ingredientes.add(Salada.JILO.getNome());
                break;
        }

        return new PratoDTO(
                prato.getNome(), // Nome do prato
                prato.getValor(), // Valor do prato
                ingredientes // Lista de ingredientes
        );
    }
}
