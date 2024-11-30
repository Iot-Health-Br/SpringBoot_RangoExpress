package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.Enum.LojaLocalização;
import com.SpringBoot_RangoExpress.Enum.StatusPedido;
import com.SpringBoot_RangoExpress.Exception.OrderWasRegistred;
import com.SpringBoot_RangoExpress.Model.Pedido;
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

    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
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

    /// ///////////////////////////////////////////////

    public List<Pedido> findAllDelivery() {
        List<Pedido> pedidosProntos = pedidoRepository.findByStatus(StatusPedido.PRONTO);

        DeliveryRouteOptimizer.OptimizedRoute optimizedRoute =
                DeliveryRouteOptimizer.optimizeRoute(
                        pedidosProntos,
                        LojaLocalização.getLatitude(),
                        LojaLocalização.getLongitude()
                );

        // Você pode acessar as informações de tempo estimado assim:
        optimizedRoute.getEstimatedDeliveryTimes().forEach((pedidoId, tempoEstimado) -> {
            System.out.printf("Pedido %d: Tempo estimado de entrega: %d minutos%n",
                    pedidoId, tempoEstimado.toMinutes());
        });

        return optimizedRoute.getRoute();
    }

}
