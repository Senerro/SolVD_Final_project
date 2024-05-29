package com.solvd.dao.impl.mybatis;

import com.solvd.dao.RouteDAO;
import com.solvd.dao.mappers.RouteMapper;
import com.solvd.entities.Route;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class RouteDAOImpl extends BaseMyBatisDAO implements RouteDAO {
    @Override
    public List<Route> findAll() {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            return routeMapper.getAllRoutes();
        }
    }

    @Override
    public List<Route> findRoutesByCityId(Long cityId) {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            return routeMapper.findRoutesByCityId(cityId);
        }
    }

    @Override
    public Optional<Route> findRouteByCities(Long departureCityId, Long arrivalCityId) {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            RouteMapper routeMapper = session.getMapper(RouteMapper.class);
            return routeMapper.findRouteByCities(departureCityId, arrivalCityId);
        }
    }
}
