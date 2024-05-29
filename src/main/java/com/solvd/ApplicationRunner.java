package com.solvd;

import lombok.experimental.UtilityClass;

import static com.solvd.service.menu.MenuRunnerService.runMainMenuFlow;

@UtilityClass
public class ApplicationRunner {

    public static void main(String[] args) {
        runMainMenuFlow();
    }
}
