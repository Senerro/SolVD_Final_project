package com.solvd.dao.mappers;

import com.solvd.entities.Route;
import com.solvd.entities.Schedule;
import com.solvd.entities.Transport;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScheduleMapper extends RouteMapper, TransportMapper {
    String FIND_SCHEDULE_BY_ROUTE_ID_QUERY = "SELECT * FROM schedule WHERE route_id = #{routeId}";

    @Select(FIND_SCHEDULE_BY_ROUTE_ID_QUERY)
    @Results(id = "scheduleMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "route", column = "route_id", javaType = Route.class,
                    one = @One(select = "findRouteById")),
            @Result(property = "transport", column = "transport_id", javaType = Transport.class,
                    one = @One(select = "findTransportById")),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<Schedule> getSchedulesByRouteId(Long routeId);
}
