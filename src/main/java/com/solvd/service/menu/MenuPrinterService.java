package com.solvd.service.menu;

import com.solvd.entities.ApplicationResult;
import com.solvd.entities.City;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.util.stream.Collectors;

import static com.solvd.common.context.DynamicContext.*;
import static com.solvd.constant.Message.*;

@UtilityClass
@Log4j2
public class MenuPrinterService {
    public static void printMainMenu() {
        log.info(HELLO_MESSAGE);
        log.info(CHOOSE_OPTION_MESSAGE);
        log.info(MENU_OPTIONS_MESSAGE);
    }

    public static void printAvailableCitiesMenu() {
        log.info(AVAILABLE_CITIES_MESSAGE);
        log.info(getAllAvailableCities().stream().map(City::getName).collect(Collectors.joining(",\n")));
        log.info("\n");
    }

    public static void printExitApplicationMessage() {
        if (getApplicationsResults().isEmpty()) {
            log.info(EMPTY_SESSION_RESULT);
        } else {
            log.info(SESSION_RESULT);
            log.info(getApplicationsResults().stream().map(ApplicationResult::toString).collect(Collectors.joining(",\n")));
        }
        log.info(EXIT_MESSAGE);
    }

    public static void printBuildRouteMenu() {
        log.info(SCHEDULE_FASTEST_MESSAGE);
        log.info(SCHEDULE_SHORTEST_MESSAGE);
        log.info(BUILD_ROUTE_OPTIONS);
    }

    public static void printBuildRouteWithStopsMenu() {
        log.info(STOPS_MENU_OPTIONS_MESSAGE);
    }
}
