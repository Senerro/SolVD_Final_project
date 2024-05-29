package com.solvd.dao.impl.mybatis;


import com.solvd.dao.CityDAO;
import com.solvd.dao.mappers.CityMapper;
import com.solvd.entities.City;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class CityDAOImpl extends BaseMyBatisDAO implements CityDAO {
    @Override
    public Optional<City> findById(Long cityId) {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.findCityById(cityId);
        }
    }

    @Override
    public Optional<City> findByName(String cityName) {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.findCityByName(cityName);
        }
    }

    @Override
    public List<City> findAll() {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession()) {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            return cityMapper.getAllCities();
        }
    }
}
