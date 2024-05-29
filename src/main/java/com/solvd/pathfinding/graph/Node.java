package com.solvd.pathfinding.graph;

import com.solvd.entities.City;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

@Getter
@Setter
@RequiredArgsConstructor
public class Node {
    private final City city;
    private final LinkedHashSet<Edge> edges = new LinkedHashSet<>();
    private final LinkedHashMap<Node, Edge> parents = new LinkedHashMap<>();

    public Long getCityId() {
        return city.getId();
    }
}
