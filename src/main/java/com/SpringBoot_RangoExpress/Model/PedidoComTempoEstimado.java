package com.SpringBoot_RangoExpress.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoComTempoEstimado extends Pedido {
    private String tempoEstimadoEntrega;
    private Integer ordemEntrega;
    private Double distanciaKm;

    public PedidoComTempoEstimado(Pedido pedido) {
        super(pedido.getIdPedido(),
                pedido.getItens(),
                pedido.getIdUsuario(),
                pedido.getNomeUsuario(),
                pedido.getEndereco(),
                pedido.getLatitude(),
                pedido.getLongitude(),
                pedido.getTotalPedido(),
                pedido.getDataPedido(),
                pedido.getStatus());
    }
}
