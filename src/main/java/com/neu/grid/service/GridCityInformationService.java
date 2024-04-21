package com.neu.grid.service;

import com.neu.grid.entity.CityInformation;

import java.util.List;

public interface GridCityInformationService {
    public List<CityInformation> selectCityById(Integer id);
    public int update(CityInformation cityInformation);
    public int insert(CityInformation cityInformation);
}
