package com.SpringBoot_RangoExpress.Controller;

import com.SpringBoot_RangoExpress.DTO.PratoDTO;
import com.SpringBoot_RangoExpress.Enum.PratoPredefinido;
import com.SpringBoot_RangoExpress.Service.CardapioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;

// Controller para expor os endpoints
@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {
    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("/hoje")
    public List<PratoDTO> getPratosDoDia() {
        return cardapioService.getPratosDoDiaDetalhado();
    }

    @GetMapping("/dia/{dia}")
    public List<PratoDTO> getPratosDoDia(@PathVariable DayOfWeek dia) {
        return cardapioService.getPratosDoDiaDetalhado(dia);
    }
}
