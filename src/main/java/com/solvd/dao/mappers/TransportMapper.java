package com.solvd.dao.mappers;

import com.solvd.entities.Transport;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

public interface TransportMapper {
    String FIND_TRANSPORT_BY_ID_QUERY = "SELECT name, type FROM transport WHERE id = #{id}";

    @Select(FIND_TRANSPORT_BY_ID_QUERY)
    @Results(id = "transportMap", value = {
        @Result(column = "name", property = "name"),
        @Result(column = "type", property = "type")
    })
    Optional<Transport> findTransportById(Long id);
}
