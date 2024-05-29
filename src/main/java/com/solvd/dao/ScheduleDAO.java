package com.solvd.dao;

import com.solvd.entities.Schedule;

import java.util.List;

public interface ScheduleDAO {
    List<Schedule> getSchedulesByRouteId(Long routeId);
}
