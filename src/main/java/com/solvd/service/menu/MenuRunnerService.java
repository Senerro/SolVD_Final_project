package com.solvd.service.menu;

import com.solvd.common.context.DynamicContext;
import com.solvd.common.exceptions.EntityNotFoundException;
import com.solvd.constant.ApplicationFlowOptionType;
import com.solvd.constant.ApplicationMenuType;
import com.solvd.constant.BuildRouteOptionType;
import com.solvd.constant.BuildRouteWithStopsOptionType;
import com.solvd.entities.*;
import com.solvd.pathfinding.graph.Graph;
import com.solvd.pathfinding.helper.SelectedWeightType;
import com.solvd.service.RouteService;
import com.solvd.service.ScheduleService;
import com.solvd.service.graphvisualizer.VisualGraph;
import com.solvd.service.graphvisualizer.VisualizerService;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.solvd.common.context.DynamicContext.getApplicationsResults;
import static com.solvd.common.context.DynamicContext.getCityByName;
import static com.solvd.constant.ApplicationFlowOptionType.getSelectedOptionFromString;
import static com.solvd.constant.ApplicationMenuType.getApplicationMenuType;
import static com.solvd.constant.BuildRouteOptionType.getBuildRouteSelectedOption;
import static com.solvd.constant.BuildRouteWithStopsOptionType.getBuildRouteWithStopsSelectedOption;
import static com.solvd.constant.InputValidationRegexType.*;
import static com.solvd.constant.Message.*;
import static com.solvd.service.input.InputReaderService.scanAndValidateUserInput;
import static com.solvd.service.input.InputValidatorService.isCityInputValid;
import static com.solvd.service.json.parser.JsonParserService.writeJsonFileToResources;
import static com.solvd.service.menu.MenuPrinterService.*;


@UtilityClass
@Log4j2
public class MenuRunnerService {
    public static void runMainMenuFlow() {
        printMainMenu();
        String mainMenuInput = scanAndValidateUserInput(MAIN_MENU_OPTIONS_VALIDATION);
        ApplicationMenuType menuType = getApplicationMenuType(mainMenuInput);
        switch (menuType) {
            case AVAILABLE_CITIES_MENU:
                runShowCitiesFlow();
                break;
            case BUILD_ROUTE_MENU:
                runProcessRouteFlow();
                break;
            default:
                runExitFlow();
                break;
        }
    }

    private static List<String> getStops(String departureCity, String destinationCity) {
        List<String> stopCities = new ArrayList<>();
        boolean addingCities = true;
        while (addingCities) {
            printBuildRouteWithStopsMenu();
            String userInput = scanAndValidateUserInput(BUILD_ROUTE_WITH_STOPS_MENU_VALIDATION);
            BuildRouteWithStopsOptionType option = getBuildRouteWithStopsSelectedOption(userInput);

            switch (option) {
                case ADD_ROUTE:
                    log.info(INPUT_STOP_MESSAGE);
                    String stopCity = scanAndValidateUserInput(INPUT_CITY_VALIDATION);

                    if (isStopCityUnique(stopCities, stopCity, departureCity, destinationCity)) {
                        stopCities.add(stopCity);
                    }
                    break;
                case EXIT:
                    addingCities = false;
                    break;
            }
        }
        return stopCities;
    }

    private static void runShowCitiesFlow() {
        printAvailableCitiesMenu();
        runSubmenuFlow();
    }

    private static void runProcessRouteFlow() {
        String departureCity = getDepartureCity();
        String destinationCity = getDestinationCity();

        runCityInputCheckFlow(departureCity, destinationCity);

        List<String> stopCities = getStops(departureCity, destinationCity);

        List<Route> routes = new RouteService().findAll();
        Graph graph = new Graph(routes);
        City departure = getCityByName(departureCity);
        City destination = getCityByName(destinationCity);

        RouteResult fastPath;
        RouteResult shortPath;
        switch (stopCities.size()) {
            case 0:
                fastPath = graph.makeFastPath(departure, destination);
                shortPath = graph.makeShortPath(departure, destination);
                break;
            case 1:
                City stop = getCityByName(stopCities.get(0));
                fastPath = graph.makeFastPathWithStopPoint(departure, destination, stop);
                shortPath = graph.makeShortPathWithStopPoint(departure, destination, stop);
                break;
            default:
                List<City> stopPoints = stopCities.stream()
                        .map(DynamicContext::getCityByName)
                        .collect(Collectors.toList());

                fastPath = graph.makeFastPathWithStopPointOrderedCollection(departure, destination, stopPoints);
                shortPath = graph.makeShortPathWithStopPointOrderedCollection(departure, destination, stopPoints);
                break;
        }

        getResult(fastPath, shortPath, departure, destination, routes);
    }

    private static void runBuildRouteMenuFlow(RouteResult fastestPath, RouteResult shortestPath) {
        printBuildRouteMenu();
        String buildRouteMenu = scanAndValidateUserInput(BUILD_ROUTE_MENU_VALIDATION);
        BuildRouteOptionType option = getBuildRouteSelectedOption(buildRouteMenu);
        switch (option) {
            case EXIT:
                runExitFlow();
                break;
            case SCHEDULE_FASTEST_ROUTE:
                runFastestScheduleRoute(fastestPath);
                break;
            case SCHEDULE_SHORTEST_ROUTE:
                runShortestScheduleRoute(shortestPath);
                break;
            case RETURN_MAIN_MENU:
                runMainMenuFlow();
                break;
        }
    }

    private static void runFastestScheduleRoute(RouteResult fastestPath) {
        try {
            ScheduleService service = new ScheduleService();
            List<Schedule> schedules = service.generateSchedule(fastestPath);
            service.printSchedule(schedules);
        } catch (EntityNotFoundException e) {
            log.error(FASTEST_ROUTE_ERROR_MESSAGE);
        }
        runSubmenuFlow();
    }

    private static void runShortestScheduleRoute(RouteResult shortestPath) {
        try {
            ScheduleService service = new ScheduleService();
            List<Schedule> schedules = service.generateSchedule(shortestPath);
            service.printSchedule(schedules);
        } catch (EntityNotFoundException e) {
            log.error(SHORTEST_ROUTE_ERROR_MESSAGE);
        }
        runSubmenuFlow();
    }

    private static void runSubmenuFlow() {
        log.info(SUBMENU_OPTIONS_MESSAGE);
        String secondMenuInput = scanAndValidateUserInput(SUBMENU_OPTIONS_VALIDATION);
        ApplicationFlowOptionType flowOption = getSelectedOptionFromString(secondMenuInput);
        switch (flowOption) {
            case EXIT:
                runExitFlow();
                break;
            default:
                runMainMenuFlow();
                break;
        }
    }

    private static void runCityInputCheckFlow(String departure, String destination) {
        if (!isCityInputValid(departure) || !isCityInputValid(destination) || departure.equalsIgnoreCase(destination)) {
            log.error(CITY_INPUT_ERROR_MESSAGE);
            runProcessRouteFlow();
        }
    }

    private static boolean isStopCityUnique(List<String> stopCities, String stopCity, String departureCity, String destinationCity) {
        if (stopCities.contains(stopCity) || stopCity.equalsIgnoreCase(departureCity) || stopCity.equalsIgnoreCase(destinationCity)) {
            log.error(STOP_CITY_INPUT_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private static void runExitFlow() {
        printExitApplicationMessage();
        writeJsonFileToResources(getApplicationsResults());
        System.exit(0);
    }

    private static String getDepartureCity() {
        log.info(INPUT_DEPARTURE_MESSAGE);
        return scanAndValidateUserInput(INPUT_CITY_VALIDATION);
    }

    private static String getDestinationCity() {
        log.info(INPUT_DESTINATION_MESSAGE);
        return scanAndValidateUserInput(INPUT_CITY_VALIDATION);
    }

    private static void prepareAndDisplayGraph(String departure, String destination, RouteResult fastPath, RouteResult shortPath, List<Route> routes) {
        VisualizerService visualizerService = new VisualizerService(VisualGraph.buildFromRoutes(routes, true));
        visualizerService.markDepartureAndArrivalNodes(departure, destination);
        visualizerService.colorGraph(SelectedWeightType.FAST, fastPath);
        visualizerService.colorGraph(SelectedWeightType.SHORT, shortPath);
        visualizerService.displayGraph();
    }

    private static void getResult(RouteResult fastPath, RouteResult shortPath, City departure, City destination, List<Route> routes) {
        log.info(RESULT_MESSAGE);
        ApplicationResult result = new ApplicationResult(fastPath, shortPath);
        getApplicationsResults().add(result);
        log.info(result.toString());

        prepareAndDisplayGraph(departure.getName(), destination.getName(), fastPath, shortPath, routes);

        runBuildRouteMenuFlow(fastPath, shortPath);
    }
}
