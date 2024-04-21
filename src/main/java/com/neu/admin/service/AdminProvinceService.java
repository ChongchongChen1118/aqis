package com.neu.admin.service;

import com.neu.admin.entity.DistrictProvince;

import java.util.List;

public interface AdminProvinceService {
    List<DistrictProvince> getAllProvince();
    public DistrictProvince getProvinceById(Integer id);
}
