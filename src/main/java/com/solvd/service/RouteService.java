package com.solvd.service;

import com.solvd.common.exceptions.EntityNotFoundException;
import com.solvd.dao.RouteDAO;
import com.solvd.dao.impl.mybatis.RouteDAOImpl;
import com.solvd.entities.Route;

import java.util.List;

public class RouteService {
    private final RouteDAO dao = new RouteDAOImpl();

    public List<Route> findAll() {
        return dao.findAll();
    }

    public List<Route> findRoutesByCityId(long cityId) throws EntityNotFoundException {
        List<Route> routesByCityId = dao.findRoutesByCityId(cityId);
        if (routesByCityId.isEmpty()) {
            throw new EntityNotFoundException("No routes found for city with ID " + cityId);
        }
        return routesByCityId;
    }

    public Route findRouteByCities(long departureCityId, long arrivalCityId) throws EntityNotFoundException {
        return dao.findRouteByCities(departureCityId, arrivalCityId).orElseThrow(
            () -> new EntityNotFoundException("No routes found for departure city with ID " + departureCityId +
                " and arrival city with ID " + arrivalCityId)
        );
    }
}
