package com.SpringBoot_RangoExpress.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "itens_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrato;

    private String nome;

    @ElementCollection
    @CollectionTable(name = "item_ingredientes")
    private List<String> ingredientes;

    private Integer quantidade;
    private BigDecimal valor;
}