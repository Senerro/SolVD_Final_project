package com.solvd.pathfinding.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Edge {
    private Node neighbour;
    private final EdgeWeight weight;
}
