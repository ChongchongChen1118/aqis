package com.neu.admin.service;

import com.neu.admin.entity.DistrictCity;

import java.util.List;

public interface AdminCityService {
    List<DistrictCity> getDistrictCityByName(String name);
    List<DistrictCity> getCityByProvince(Integer id );
}
