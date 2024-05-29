package com.solvd.constant;

public enum BuildRouteOptionType {
    SCHEDULE_FASTEST_ROUTE,
    SCHEDULE_SHORTEST_ROUTE,
    RETURN_MAIN_MENU,
    EXIT;

    public static BuildRouteOptionType getBuildRouteSelectedOption(String option) {
        switch (option) {
            case "1":
                return SCHEDULE_FASTEST_ROUTE;
            case "2":
                return SCHEDULE_SHORTEST_ROUTE;
            case "3":
                return RETURN_MAIN_MENU;
            default:
                return EXIT;
        }
    }
}
