package com.solvd.service;

import com.solvd.common.exceptions.EntityNotFoundException;
import com.solvd.dao.CityDAO;
import com.solvd.dao.impl.mybatis.CityDAOImpl;
import com.solvd.entities.City;

import java.util.List;

public class CityService {
    private final CityDAO dao = new CityDAOImpl();

    public List<City> findAll() {
        return dao.findAll();
    }

    public City findById(long id) throws EntityNotFoundException {
        return dao.findById(id).orElseThrow(
            () -> new EntityNotFoundException("City with ID " + id + " not found")
        );
    }

    public City findByName(String name) throws EntityNotFoundException {
        return dao.findByName(name).orElseThrow(
            () -> new EntityNotFoundException("City with name " + name + " not found")
        );
    }
}
