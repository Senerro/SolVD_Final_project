package com.solvd.dao.mappers;

import com.solvd.entities.City;
import com.solvd.entities.Route;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface RouteMapper extends CityMapper {
    String FIND_ROUTES_BY_CITY_ID_QUERY = "SELECT * FROM route WHERE departure_city_id = #{cityId} OR arrival_city_id = #{cityId}";
    String FIND_ALL_QUERY = "SELECT * FROM route";
    String FIND_ROUTE_BY_ID_QUERY = "SELECT * FROM route WHERE id = #{id}";
    String FIND_ROUTE_BY_CITIES_QUERY = "SELECT * FROM route " +
        "WHERE departure_city_id = #{departureCityId} AND arrival_city_id = #{arrivalCityId} " +
        "OR departure_city_id = #{arrivalCityId} AND arrival_city_id = #{departureCityId}";


    @Select(FIND_ROUTES_BY_CITY_ID_QUERY)
    @Results(id = "routeMap", value = {
        @Result(column = "id", property = "id"),
        @Result(column = "departure_city_id", property = "departureCity", javaType = City.class,
            one = @One(select = "findCityById")),
        @Result(column = "arrival_city_id", property = "arrivalCity", javaType = City.class,
            one = @One(select = "findCityById")),
        @Result(column = "time_in_minutes", property = "transitTimeMinutes"),
        @Result(column = "distance", property = "distance")
    })
    List<Route> findRoutesByCityId(Long cityId);

    @Select(FIND_ALL_QUERY)
    @ResultMap("routeMap")
    List<Route> getAllRoutes();

    @Select(FIND_ROUTE_BY_ID_QUERY)
    @ResultMap("routeMap")
    Optional<Route> findRouteById(Long id);

    @Select(FIND_ROUTE_BY_CITIES_QUERY)
    @ResultMap("routeMap")
    Optional<Route> findRouteByCities(@Param("departureCityId") Long departureCityId, @Param("arrivalCityId") Long arrivalCityId);
}
