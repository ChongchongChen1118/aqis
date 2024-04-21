package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.admin.entity.DistrictCity;
import com.neu.admin.mapper.*;
import com.neu.admin.service.AdminCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCityServiceImpl implements AdminCityService {
    @Autowired
    AdminIdeaMapper adminIdeaMapper;
    @Autowired
    AdminIdentInspectorMapper adminIdentInspectorMapper;
    @Autowired
    AdminDistrictCityMapper adminDistrictCityMapper;
    @Autowired
    AdminDistrictProvinceMapper adminDistrictProvinceMapper;
    @Autowired
    AdminIdentUserMapper adminIdentUserMapperUser;
    @Autowired
    AdminAqiMapper adminAqiMapper;

    @Override
    public List<DistrictCity> getDistrictCityByName(String name) {
        QueryWrapper<DistrictCity> districtCityQueryWrapper=new QueryWrapper<>();
        districtCityQueryWrapper.like("ci_name",name);
        List <DistrictCity> districtCities= adminDistrictCityMapper.selectList(districtCityQueryWrapper);
        return districtCities;
    }


    @Override
    public List<DistrictCity> getCityByProvince(Integer id) {
        QueryWrapper<DistrictCity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ci_cid",id);
        List<DistrictCity>list= adminDistrictCityMapper.selectList(queryWrapper);
        return list;
    }
}
