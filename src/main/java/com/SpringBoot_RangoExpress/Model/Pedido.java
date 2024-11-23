package com.SpringBoot_RangoExpress.Model;

import com.SpringBoot_RangoExpress.Enum.PratoPredefinido;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Pedido {
    private Long id;
    private PratoPredefinido pratoEscolhido;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private BigDecimal valorTotal;

    public enum StatusPedido {
        RECEBIDO,
        EM_PREPARACAO,
        PRONTO,
        ENTREGUE,
        CANCELADO
    }

    public Pedido(PratoPredefinido pratoEscolhido) {
        this.pratoEscolhido = pratoEscolhido;
        this.dataPedido = LocalDateTime.now();
        this.status = StatusPedido.RECEBIDO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PratoPredefinido getPratoEscolhido() {
        return pratoEscolhido;
    }

    public void setPratoEscolhido(PratoPredefinido pratoEscolhido) {
        this.pratoEscolhido = pratoEscolhido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", pratoEscolhido=" + pratoEscolhido +
                ", dataPedido=" + dataPedido +
                ", status=" + status +
                ", valorTotal=" + valorTotal +
                '}';
    }

    // Getters e setters
}
