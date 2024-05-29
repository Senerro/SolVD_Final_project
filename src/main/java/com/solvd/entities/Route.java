package com.solvd.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Route {
    private Long id;
    private City departureCity;
    private City arrivalCity;
    private Long transitTimeMinutes;
    private BigDecimal distance;

    public Long getDepartureCityId() {
        return departureCity.getId();
    }

    public String getDepartureCityName() {
        return departureCity.getName();
    }

    public Long getArrivalCityId() {
        return arrivalCity.getId();
    }

    public String getArrivalCityName() {
        return arrivalCity.getName();
    }
}
