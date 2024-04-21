package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.admin.service.AdminProvinceService;
import com.neu.admin.entity.DistrictProvince;
import com.neu.admin.mapper.AdminDistrictProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminProvinceServiceImpl implements AdminProvinceService {

    @Autowired
    AdminDistrictProvinceMapper adminDistrictProvinceMapper;
    @Override
    public List<DistrictProvince> getAllProvince() {
        List<DistrictProvince> list= adminDistrictProvinceMapper.selectList(null);
        return list;
    }
    @Override
    public DistrictProvince getProvinceById(Integer id) {
        QueryWrapper queryWrapper=new QueryWrapper<DistrictProvince>();
        queryWrapper.eq("pr_id",id);
        return adminDistrictProvinceMapper.selectOne(queryWrapper);
    }
}
