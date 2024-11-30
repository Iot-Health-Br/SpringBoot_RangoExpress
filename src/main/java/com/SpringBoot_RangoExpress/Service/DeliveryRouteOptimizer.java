package com.SpringBoot_RangoExpress.Service;

import com.SpringBoot_RangoExpress.Model.Pedido;
import java.util.*;
import java.time.Duration;

public class DeliveryRouteOptimizer {
    private static final double EARTH_RADIUS = 6371; // Raio da Terra em km
    private static final double AVERAGE_SPEED = 30.0; // Velocidade média em km/h
    private static final double DELIVERY_TIME = 5.0; // Tempo médio de entrega em minutos

    public static class OptimizedRoute {
        private final List<Pedido> route;
        private final double totalDistance;
        private final Duration estimatedTotalTime;
        private final Map<Long, Duration> estimatedDeliveryTimes;

        public OptimizedRoute(List<Pedido> route, double totalDistance,
                              Duration estimatedTotalTime,
                              Map<Long, Duration> estimatedDeliveryTimes) {
            this.route = route;
            this.totalDistance = totalDistance;
            this.estimatedTotalTime = estimatedTotalTime;
            this.estimatedDeliveryTimes = estimatedDeliveryTimes;
        }

        public List<Pedido> getRoute() {
            return route;
        }

        public double getTotalDistance() {
            return totalDistance;
        }

        public Duration getEstimatedTotalTime() {
            return estimatedTotalTime;
        }

        public Map<Long, Duration> getEstimatedDeliveryTimes() {
            return estimatedDeliveryTimes;
        }
    }

    // Calcula a distância entre dois pontos usando a fórmula de Haversine
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    // Calcula o tempo estimado de viagem entre dois pontos
    private static Duration calculateTravelTime(double distance) {
        double timeInHours = distance / AVERAGE_SPEED;
        return Duration.ofMinutes((long)(timeInHours * 60));
    }

    public static OptimizedRoute optimizeRoute(List<Pedido> pedidos,
                                               double storeLatitude,
                                               double storeLongitude) {
        int n = pedidos.size();

        // Cria matriz de distâncias
        double[][] distances = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                    continue;
                }

                double lat1 = (i == 0) ? storeLatitude : pedidos.get(i - 1).getLatitude();
                double lon1 = (i == 0) ? storeLongitude : pedidos.get(i - 1).getLongitude();
                double lat2 = (j == 0) ? storeLatitude : pedidos.get(j - 1).getLatitude();
                double lon2 = (j == 0) ? storeLongitude : pedidos.get(j - 1).getLongitude();

                distances[i][j] = calculateDistance(lat1, lon1, lat2, lon2);
            }
        }

        // Implementação do algoritmo TSP com programação dinâmica
        int numberOfPoints = n + 1;
        int completeMask = (1 << numberOfPoints) - 1;

        Map<String, Double> dp = new HashMap<>();
        Map<String, Integer> next = new HashMap<>();

        // Encontra o melhor caminho
        solveTSP(0, 1, distances, dp, next, completeMask, numberOfPoints);

        // Reconstrói o caminho otimizado
        List<Pedido> optimizedRoute = new ArrayList<>();
        Map<Long, Duration> estimatedDeliveryTimes = new HashMap<>();

        int currentPos = 0;
        int mask = 1;
        double totalDistance = 0;
        Duration currentTime = Duration.ZERO;

        while (mask != completeMask) {
            String key = currentPos + "," + mask;
            int nextPos = next.get(key);

            if (nextPos != 0) {
                Pedido nextPedido = pedidos.get(nextPos - 1);
                optimizedRoute.add(nextPedido);

                // Calcula distância e tempo até este ponto
                double distance = distances[currentPos][nextPos];
                totalDistance += distance;

                // Adiciona tempo de viagem e tempo de entrega
                currentTime = currentTime.plus(calculateTravelTime(distance))
                        .plusMinutes((long)DELIVERY_TIME);

                estimatedDeliveryTimes.put(nextPedido.getIdPedido(), currentTime);
            }

            mask |= (1 << nextPos);
            currentPos = nextPos;
        }

        // Adiciona distância e tempo de retorno à loja
        totalDistance += distances[currentPos][0];
        currentTime = currentTime.plus(calculateTravelTime(distances[currentPos][0]));

        return new OptimizedRoute(optimizedRoute, totalDistance, currentTime, estimatedDeliveryTimes);
    }

    private static double solveTSP(int pos, int mask, double[][] distances,
                                   Map<String, Double> dp, Map<String, Integer> next,
                                   int completeMask, int n) {
        if (mask == completeMask) {
            return distances[pos][0];
        }

        String key = pos + "," + mask;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        double minDist = Double.MAX_VALUE;
        int bestNext = -1;

        for (int nextPos = 0; nextPos < n; nextPos++) {
            if ((mask & (1 << nextPos)) == 0) {
                double currentDist = distances[pos][nextPos] +
                        solveTSP(nextPos, mask | (1 << nextPos), distances, dp, next, completeMask, n);

                if (currentDist < minDist) {
                    minDist = currentDist;
                    bestNext = nextPos;
                }
            }
        }

        dp.put(key, minDist);
        next.put(key, bestNext);

        return minDist;
    }
}
