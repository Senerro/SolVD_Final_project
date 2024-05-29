package com.solvd.dao;

import com.solvd.entities.City;

import java.util.Optional;
import java.util.List;

public interface CityDAO {
    Optional<City> findById(Long cityId);
    Optional<City> findByName(String cityName);
    List<City> findAll();
}
