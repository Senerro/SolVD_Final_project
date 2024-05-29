package com.solvd.dao;

import com.solvd.entities.Route;

import java.util.List;
import java.util.Optional;

public interface RouteDAO {
    List<Route> findAll();
    List<Route> findRoutesByCityId(Long cityId);
    Optional<Route> findRouteByCities(Long departureCityId, Long arrivalCityId);
}

