package com.solvd.pathfinding.helper;

import com.solvd.entities.City;
import com.solvd.entities.RouteResult;
import com.solvd.pathfinding.graph.Node;
import com.solvd.pathfinding.graph.EdgeWeight;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@UtilityClass
public class RouteResultHelper {
    private List<City> convertToCity(List<Node> route) {
        List<City> cities = new ArrayList<>();
        for (var element : route) {
            cities.add(element.getCity());
        }
        return cities;
    }

    private BigDecimal convertDoubleToBigDecimal(double distance) {
        return BigDecimal.valueOf(distance);
    }

    public RouteResult makeResult(LinkedList<Node> nodes, EdgeWeight weight) {
        return new RouteResult()
                .setCities(convertToCity(nodes))
                .setDistance(convertDoubleToBigDecimal(weight.getDistance()))
                .setTransitTimeMinutes((long) weight.getTime());
    }

    public RouteResult resultSummation(RouteResult first, RouteResult second) {
        RouteResult result = new RouteResult();
        LinkedList<City> cities = new LinkedList<>(first.getCities());
        if (!first.getCities().isEmpty())
            cities.removeLast();
        cities.addAll(second.getCities());

        result.setCities(cities);
        result.setTransitTimeMinutes((first.getTransitTimeMinutes() + second.getTransitTimeMinutes()));
        result.setDistance(BigDecimal.valueOf(first.getDistance().doubleValue()).add(second.getDistance()));
        return result;
    }

    public RouteResult resultCollectionSummation(List<RouteResult> list) {
        RouteResult result = getDefaultRouteResult();

        while (!list.isEmpty()) {
            result = resultSummation(result, list.get(0));
            list.remove(0);
        }
        return result;
    }

    private RouteResult getDefaultRouteResult() {
        return new RouteResult()
                .setCities(new ArrayList<>())
                .setDistance(convertDoubleToBigDecimal(0f))
                .setTransitTimeMinutes(0L);
    }
}
