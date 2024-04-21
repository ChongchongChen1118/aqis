package com.neu.users.service;

import com.neu.users.entity.DistrictCity;
import com.neu.users.entity.DistrictProvince;
import com.neu.users.entity.IdentInspector;

import java.util.List;

public interface DistrictProvinceService {
    public List<DistrictProvince> selectProvince();
    public List<DistrictCity> selectCityBuId(String cid);
    public String selectPrById(String id);
    public List<IdentInspector> seleceSeven();
}
