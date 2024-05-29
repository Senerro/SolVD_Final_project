package com.solvd.entities;

import lombok.Data;

@Data
public class City {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
