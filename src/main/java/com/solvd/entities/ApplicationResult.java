package com.solvd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResult {
    private RouteResult fastestRoute;
    private RouteResult shortestRoute;

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Processed route:\n")
            .append("Shortest requested route: ").append(shortestRoute)
            .append("Fastest requested route: ").append(fastestRoute)
            .toString();
    }
}
