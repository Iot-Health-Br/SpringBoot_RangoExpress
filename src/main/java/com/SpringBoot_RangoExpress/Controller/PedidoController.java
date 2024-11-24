package com.SpringBoot_RangoExpress.Controller;


import com.SpringBoot_RangoExpress.Exception.OrderWasRegistred;
import com.SpringBoot_RangoExpress.Model.Pedido;
import com.SpringBoot_RangoExpress.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    ///////// ********** C.R.U.D ********** ////////////

    @PostMapping("/pedidos")
    public ResponseEntity<String> createPedido(@RequestBody Pedido pedido) {
        try{
            String savedPedido = pedidoService.savePedido(pedido);
            return ResponseEntity.ok(savedPedido);
        }catch (OrderWasRegistred e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao salvar a pessoa.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.findAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.findPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido updatedPedido = pedidoService.updatePedido(id, pedido);
            return ResponseEntity.ok(updatedPedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
