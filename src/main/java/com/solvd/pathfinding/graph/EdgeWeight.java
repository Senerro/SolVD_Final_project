package com.solvd.pathfinding.graph;

import com.solvd.pathfinding.helper.SelectedWeightType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class EdgeWeight {
    private final double distance;
    private final double time;

    public EdgeWeight(final BigDecimal distance, final Long transitTime) {
        this.distance = distance.doubleValue();
        this.time = transitTime;
    }

    public EdgeWeight() {
        distance = 0d;
        time = 0d;
    }

    public boolean isMAX() {
        return distance == Float.MAX_VALUE && this.time == Float.MAX_VALUE;
    }

    public double weightValue(SelectedWeightType weightType) {
        switch (weightType) {
            case FAST:
                return this.time;
            case SHORT:
                return this.distance;
            default:
                throw new IllegalArgumentException("Unhandled weight type");
        }
    }
}
