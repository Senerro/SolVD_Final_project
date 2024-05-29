package com.solvd.pathfinding.helper;

import com.solvd.pathfinding.graph.EdgeWeight;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EdgeWeightHelper {

    public EdgeWeight summation(EdgeWeight first, EdgeWeight second) {
        return new EdgeWeight(first.getDistance() + second.getDistance(), first.getTime() + second.getTime());
    }

    public boolean isLessWeight(EdgeWeight first, EdgeWeight second, SelectedWeightType weightType) {
        return first.weightValue(weightType) < second.weightValue(weightType);
    }

    public EdgeWeight MAX() {
        return new EdgeWeight(Float.MAX_VALUE, Float.MAX_VALUE);
    }
}
