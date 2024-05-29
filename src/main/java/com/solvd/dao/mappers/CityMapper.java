package com.solvd.dao.mappers;


import com.solvd.entities.City;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface CityMapper {
    String FIND_BY_ID_QUERY = "SELECT * FROM city WHERE id=#{cityId}";
    String FIND_BY_NAME_QUERY = "SELECT * FROM city WHERE name=#{cityName}";
    String FIND_ALL_QUERY = "SELECT * FROM city";

    @Select(FIND_BY_ID_QUERY)
    @Results(id = "cityMap", value = {
        @Result(column = "id", property = "id"),
        @Result(column = "name", property = "name")
    })
    Optional<City> findCityById(Long cityId);

    @Select(FIND_BY_NAME_QUERY)
    @ResultMap("cityMap")
    Optional<City> findCityByName(String cityName);

    @Select(FIND_ALL_QUERY)
    @ResultMap("cityMap")
    List<City> getAllCities();
}
