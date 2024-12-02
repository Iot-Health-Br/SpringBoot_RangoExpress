package com.SpringBoot_RangoExpress.Service.MakeOrder;

import com.SpringBoot_RangoExpress.Exception.OrderWasRegistred;
import com.SpringBoot_RangoExpress.Model.Pedido;
import com.SpringBoot_RangoExpress.Repository.PedidoRepository;
import com.SpringBoot_RangoExpress.Service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePedido_WhenValidPedido_ReturnSuccessMessage() throws OrderWasRegistred {
        // Arrange
        Pedido pedido = new Pedido();
        // Configurar os campos necessários do pedido aqui

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // Act
        String result = pedidoService.savePedido(pedido);

        // Assert
        assertEquals("Pedido cadastrado com sucesso!", result);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void savePedido_WhenRepositoryFails_ThrowsException() {
        // Arrange
        Pedido pedido = new Pedido();
        // Configurar os campos necessários do pedido aqui

        when(pedidoRepository.save(any(Pedido.class)))
                .thenThrow(new RuntimeException("Erro ao salvar"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> pedidoService.savePedido(pedido));
        verify(pedidoRepository, times(1)).save(pedido);
    }
}
