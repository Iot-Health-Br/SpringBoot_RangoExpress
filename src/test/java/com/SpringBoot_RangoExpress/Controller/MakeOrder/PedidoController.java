package com.SpringBoot_RangoExpress.Controller.MakeOrder;

import com.SpringBoot_RangoExpress.Controller.PedidoController;
import com.SpringBoot_RangoExpress.Exception.OrderWasRegistred;
import com.SpringBoot_RangoExpress.Model.Pedido;
import com.SpringBoot_RangoExpress.Service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPedido_WhenValidPedido_ReturnsOk() throws OrderWasRegistred {
        // Arrange
        Pedido pedido = new Pedido();
        // Configurar os campos necessários do pedido aqui

        when(pedidoService.savePedido(any(Pedido.class)))
                .thenReturn("Pedido cadastrado com sucesso!");

        // Act
        ResponseEntity<String> response = pedidoController.createPedido(pedido);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pedido cadastrado com sucesso!", response.getBody());
    }

    @Test
    void createPedido_WhenPedidoAlreadyExists_ReturnsBadRequest() throws OrderWasRegistred {
        // Arrange
        Pedido pedido = new Pedido();
        // Configurar os campos necessários do pedido aqui

        when(pedidoService.savePedido(any(Pedido.class)))
                .thenThrow(new OrderWasRegistred("Pedido já registrado!"));

        // Act
        ResponseEntity<String> response = pedidoController.createPedido(pedido);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Pedido já registrado!", response.getBody());
    }

    @Test
    void createPedido_WhenInternalError_ReturnsInternalServerError() throws OrderWasRegistred {
        // Arrange
        Pedido pedido = new Pedido();
        // Configurar os campos necessários do pedido aqui

        when(pedidoService.savePedido(any(Pedido.class)))
                .thenThrow(new RuntimeException("Erro interno"));

        // Act
        ResponseEntity<String> response = pedidoController.createPedido(pedido);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno ao salvar a pessoa.", response.getBody());
    }
}