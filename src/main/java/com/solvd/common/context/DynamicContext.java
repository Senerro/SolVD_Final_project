package com.solvd.common.context;

import com.solvd.entities.ApplicationResult;
import com.solvd.entities.City;
import com.solvd.service.CityService;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import static com.solvd.constant.Message.CITY_READ_ERROR_MESSAGE;

@UtilityClass
@Log4j2
public class DynamicContext {
    private static List<ApplicationResult> applicationResults = null;
    private static List<City> cityList = null;

    public static List<ApplicationResult> getApplicationsResults() {
        initResults();
        return applicationResults;
    }

    public static List<City> getAllAvailableCities() {
        initCities();
        return cityList;
    }

    public static City getCityByName(String cityName) {
        initCities();
        return cityList.stream().filter(city -> city.getName().equalsIgnoreCase(cityName)).findFirst().get();
    }

    private void initResults() {
        if (applicationResults == null) {
            applicationResults = new ArrayList<>();
        }
    }

    private void initCities() {
        try {
            if (cityList == null) {
                cityList = new CityService().findAll();
            }
        } catch (Exception e) {
            log.error(CITY_READ_ERROR_MESSAGE);
            System.exit(1);
        }
    }
}