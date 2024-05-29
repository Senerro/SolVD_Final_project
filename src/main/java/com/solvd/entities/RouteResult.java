package com.solvd.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"cities", "distance", "transitTime"})
public class RouteResult {
    private List<City> cities;
    private BigDecimal distance;
    private Long transitTimeMinutes;

    @Override
    public String toString() {
        long hours = transitTimeMinutes / 60;
        long minutes = transitTimeMinutes % 60;
        return new StringBuilder()
            .append("Route: ").append(cities.stream().map(City::getName).collect(Collectors.joining(" -> ")))
            .append(", Distance to travel: ").append(distance).append(" km, ")
            .append("Time in transit: ").append(hours).append(" hours ")
            .append(minutes).append(" minutes\n")
            .toString();
    }
}
