package com.solvd.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Message {
    public static final String HELLO_MESSAGE = "WELCOME TO NAVIGATOR APPLICATION!\n";
    public static final String CHOOSE_OPTION_MESSAGE = "What would you like to do:\n";
    public static final String MENU_OPTIONS_MESSAGE = "1. Show available cities.\n2. Build a route.\n0. Exit from application.";
    public static final String STOPS_MENU_OPTIONS_MESSAGE = "1. Add a stop\n0. Finish and get the result";
    public static final String AVAILABLE_CITIES_MESSAGE = "At the moment you can choose among next cities:";
    public static final String SCHEDULE_FASTEST_MESSAGE = "1. Get transport and schedules for fastest route.";
    public static final String SCHEDULE_SHORTEST_MESSAGE = "2. Get transport and schedules for shortest route.";
    public static final String BUILD_ROUTE_OPTIONS = "3. Return to main menu.\n0. Exit from application.";
    public static final String SUBMENU_OPTIONS_MESSAGE = "1. Return to main menu.\n0. Exit from application.";
    public static final String INPUT_DEPARTURE_MESSAGE = "Please input city of your departure >>>";
    public static final String INPUT_DESTINATION_MESSAGE = "Please input city of your destination >>>";
    public static final String INPUT_STOP_MESSAGE = "Please input city where you want to stop >>>";
    public static final String RESULT_MESSAGE = "Thank you! Processing data...Done!\n";
    public static final String SESSION_RESULT = "During this session you processed following routes:\n";
    public static final String EMPTY_SESSION_RESULT = "Unfortunately, you didn't calculate any routes during this session.";
    public static final String EXIT_MESSAGE = "Thank you for using our application. Bye-bye!";
    public static final String CITY_INPUT_ERROR_MESSAGE = "There is no such city or the destination is the same as departure! Lets try again!";
    public static final String STOP_CITY_INPUT_ERROR_MESSAGE = "Stop city must be unique and different from departure and destination cities.";
    public static final String CITY_READ_ERROR_MESSAGE = "Unable to get all available cities, please restart application!";
    public static final String FASTEST_ROUTE_ERROR_MESSAGE = "Unfortunately can't print schedule for fastest route, try another time :(";
    public static final String SHORTEST_ROUTE_ERROR_MESSAGE = "Unfortunately can't print schedule for shortest route, try another time :(";
    public static final String INPUT_ERROR_MESSAGE = "Invalid input! Please check your input and try again.";
}
