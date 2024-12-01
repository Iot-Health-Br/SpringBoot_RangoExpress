package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.Enum.LojaLocalização;
import com.SpringBoot_RangoExpress.Enum.StatusPedido;
import com.SpringBoot_RangoExpress.Exception.OrderWasRegistred;
import com.SpringBoot_RangoExpress.Model.Pedido;
import com.SpringBoot_RangoExpress.Model.PedidoComTempoEstimado;
import com.SpringBoot_RangoExpress.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    private static final double RAIO_TERRA_KM = 6371.0;

    ///////// ********** C.R.U.D ********** ////////////

    public String savePedido(Pedido pedido)throws OrderWasRegistred {
        pedidoRepository.save(pedido);
        return "Pedido cadastrado com sucesso!";
    }

    // Lista de Pedidos
    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
    }

    // Lista de Pedidos do Usuário
    public List<Pedido> findAllOrderUser(Long userId) {
        return pedidoRepository.findByIdUsuario(userId);
    }

    // Lista de Avaliação de Entrega dos Pedidos do Usuário
    public List<Pedido> findAllOrderUserForAvaliation(Long userId) {
        return pedidoRepository.findByIdUsuarioAndStatus(userId, StatusPedido.ENTREGUE);
    }

    public Optional<Pedido> findPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido updatePedido(Long id, Pedido pedidoAtualizado) {
        System.out.println("Service: "+pedidoAtualizado);
        return pedidoRepository.findById(id)
                .map(pedidoExistente -> {
                    pedidoExistente.setStatus(pedidoAtualizado.getStatus());
                    return pedidoRepository.save(pedidoExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + id));
    }

    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new IllegalArgumentException("Pedido não encontrado com ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    // Lista de Entregas

    public List<PedidoComTempoEstimado> findAllDelivery() {
        List<Pedido> pedidosProntos = pedidoRepository.findByStatus(StatusPedido.PRONTO);

        return com.SpringBoot_RangoExpress.Service.DeliveryRouteOptimizer.optimizeDeliveryRoute(
                pedidosProntos,
                LojaLocalização.getLatitude(),
                LojaLocalização.getLongitude()
        );
    }

    // Lista de Avaliações Pedidos Entregue
    public List<Pedido> findAllOrderDelivered() {
        return pedidoRepository.findByStatus(StatusPedido.valueOf("ENTREGUE"));
    }

}
