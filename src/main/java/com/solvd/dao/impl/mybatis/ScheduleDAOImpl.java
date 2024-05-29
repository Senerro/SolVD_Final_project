package com.solvd.dao.impl.mybatis;

import com.solvd.dao.ScheduleDAO;
import com.solvd.dao.mappers.ScheduleMapper;
import com.solvd.entities.Schedule;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ScheduleDAOImpl extends BaseMyBatisDAO implements ScheduleDAO {
    @Override
    public List<Schedule> getSchedulesByRouteId(Long routeId) {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            ScheduleMapper scheduleMapper = session.getMapper(ScheduleMapper.class);
            return scheduleMapper.getSchedulesByRouteId(routeId);
        }
    }
}
