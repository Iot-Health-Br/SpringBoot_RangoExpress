package com.SpringBoot_RangoExpress.Controller;

import com.SpringBoot_RangoExpress.DTO.PratoDTO;
import com.SpringBoot_RangoExpress.Service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

// Controller para expor os endpoints
@RestController
@RequestMapping("/api/cardapio")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST}) // Aplica CORS para todos os métodos desse controlador
public class CardapioController {
    @Autowired
    private CardapioService cardapioService;

    @GetMapping("/hoje")
    public List<PratoDTO> getPratosDoDia() {
        return cardapioService.getPratosDoDiaDetalhado();
    }

    @GetMapping("/dia/{dia}")
    public List<PratoDTO> getPratosDoDia(@PathVariable DayOfWeek dia) {
        return cardapioService.getPratosDoDiaDetalhado(dia);
    }
}
