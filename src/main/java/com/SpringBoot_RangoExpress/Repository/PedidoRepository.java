package com.SpringBoot_RangoExpress.Repository;


import com.SpringBoot_RangoExpress.Enum.StatusPedido;
import com.SpringBoot_RangoExpress.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Métodos personalizados de consulta podem ser adicionados aqui
    List<Pedido> findByStatus(StatusPedido status);
    List<Pedido> findByIdUsuario(long idUsuario);
    List<Pedido> findByIdUsuarioAndStatus(long idUsuario, StatusPedido status);
    List<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim);
}


