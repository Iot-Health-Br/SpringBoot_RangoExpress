package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.Model.Pedido;
import com.SpringBoot_RangoExpress.Model.PedidoComTempoEstimado;
import java.util.*;

public class DeliveryRouteOptimizer {
    private static final double EARTH_RADIUS = 6371; // Raio da Terra em km
    private static final double AVERAGE_SPEED = 30.0; // Velocidade média em km/h
    private static final int DELIVERY_TIME_MINUTES = 5; // Tempo de entrega em cada parada

    public static List<PedidoComTempoEstimado> optimizeDeliveryRoute(List<Pedido> pedidos,
                                                                     double storeLatitude,
                                                                     double storeLongitude) {
        List<PedidoComTempoEstimado> rotaOtimizada = new ArrayList<>();
        List<Pedido> pedidosRestantes = new ArrayList<>(pedidos);

        // Ponto atual começa na loja
        double currentLat = storeLatitude;
        double currentLong = storeLongitude;

        // Tempo acumulado em minutos
        double tempoAcumulado = 0;

        // Para cada posição na rota
        for (int ordem = 1; ordem <= pedidos.size(); ordem++) {
            // Encontra o pedido mais próximo do ponto atual
            Pedido pedidoMaisProximo = null;
            double menorDistancia = Double.MAX_VALUE;

            for (Pedido pedido : pedidosRestantes) {
                double distancia = calculateDistance(currentLat, currentLong,
                        pedido.getLatitude(), pedido.getLongitude());
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    pedidoMaisProximo = pedido;
                }
            }

            if (pedidoMaisProximo != null) {
                // Calcula tempo até este ponto
                double tempoViagem = calculateTravelTime(menorDistancia);
                tempoAcumulado += tempoViagem + DELIVERY_TIME_MINUTES;

                // Cria objeto com informações de entrega
                PedidoComTempoEstimado pedidoComTempo = new PedidoComTempoEstimado(pedidoMaisProximo);
                pedidoComTempo.setOrdemEntrega(ordem);
                pedidoComTempo.setDistanciaKm(Math.round(menorDistancia * 100.0) / 100.0);
                pedidoComTempo.setTempoEstimadoEntrega(formatTempo(tempoAcumulado));

                rotaOtimizada.add(pedidoComTempo);

                // Atualiza posição atual e remove pedido da lista de pendentes
                currentLat = pedidoMaisProximo.getLatitude();
                currentLong = pedidoMaisProximo.getLongitude();
                pedidosRestantes.remove(pedidoMaisProximo);
            }
        }

        return rotaOtimizada;
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private static double calculateTravelTime(double distance) {
        return (distance / AVERAGE_SPEED) * 60; // Converte para minutos
    }

    private static String formatTempo(double minutos) {
        int horas = (int) (minutos / 60);
        int mins = (int) (minutos % 60);

        if (horas > 0) {
            return String.format("%dh%02dm", horas, mins);
        } else {
            return String.format("%dm", mins);
        }
    }
}