package com.solvd.entities;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Schedule {
    private Long id;
    private Route route;
    private Transport transport;
    private LocalTime departureTime;
}
