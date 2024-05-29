package com.solvd.service;

import com.solvd.common.exceptions.EntityNotFoundException;
import com.solvd.dao.ScheduleDAO;
import com.solvd.dao.impl.mybatis.ScheduleDAOImpl;
import com.solvd.entities.City;
import com.solvd.entities.RouteResult;
import com.solvd.entities.Schedule;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ScheduleService {
    private final ScheduleDAO dao = new ScheduleDAOImpl();
    private final RouteService routeService = new RouteService();

    public List<Schedule> generateSchedule(RouteResult routeResult) throws EntityNotFoundException {
        List<City> cities = routeResult.getCities();
        List<Schedule> schedules = new ArrayList<>();

        for (int i = 0; i < cities.size() - 1; i++) {
            City departureCity = cities.get(i);
            City arrivalCity = cities.get(i + 1);
            Long routeId = getRouteId(departureCity, arrivalCity);
            if (routeId != null) {
                List<Schedule> routeSchedules = dao.getSchedulesByRouteId(routeId);
                schedules.addAll(routeSchedules);
            }
        }
        return schedules;
    }

    public void printSchedule(List<Schedule> schedules) {
        Map<Long, List<Schedule>> groupedSchedules = groupSchedulesByRoute(schedules);
        for (Map.Entry<Long, List<Schedule>> entry : groupedSchedules.entrySet()) {
            List<Schedule> routeSchedules = entry.getValue();
            printRouteSchedule(routeSchedules);
        }
    }

    private Long getRouteId(City departureCity, City arrivalCity) throws EntityNotFoundException {
        return routeService.findRouteByCities(departureCity.getId(), arrivalCity.getId()).getId();
    }

    private Map<Long, List<Schedule>> groupSchedulesByRoute(List<Schedule> schedules) {
        Map<Long, List<Schedule>> groupedSchedules = new HashMap<>();
        for (Schedule schedule : schedules) {
            Long routeId = schedule.getRoute().getId();
            groupedSchedules.computeIfAbsent(routeId, k -> new ArrayList<>()).add(schedule);
        }
        return groupedSchedules;
    }

    private void printRouteSchedule(List<Schedule> routeSchedules) {
        if (routeSchedules.isEmpty()) {
            return;
        }
        Schedule firstSchedule = routeSchedules.get(0);
        log.info("{} -> {}:", firstSchedule.getRoute().getDepartureCity().getName(),
            firstSchedule.getRoute().getArrivalCity().getName());
        for (Schedule schedule : routeSchedules) {
            log.info("    {} {}: departure time: {}",
                schedule.getTransport().getName(),
                schedule.getTransport().getType(),
                schedule.getDepartureTime());
        }
    }
}
