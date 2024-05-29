package com.solvd.constant;

public enum BuildRouteWithStopsOptionType {
    ADD_ROUTE,
    EXIT;

    public static BuildRouteWithStopsOptionType getBuildRouteWithStopsSelectedOption(String option) {
        switch (option) {
            case "1":
                return ADD_ROUTE;
            default:
                return EXIT;
        }
    }
}