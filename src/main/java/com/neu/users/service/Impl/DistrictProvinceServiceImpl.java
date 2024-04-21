package com.neu.users.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.users.entity.DistrictCity;
import com.neu.users.entity.DistrictProvince;
import com.neu.users.entity.IdentInspector;
import com.neu.users.mapper.DistrictCityMapper;
import com.neu.users.mapper.DistrictProvinceMapper;
import com.neu.users.mapper.IdentInspectorMapper;
import com.neu.users.service.DistrictProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictProvinceServiceImpl implements DistrictProvinceService {
    @Autowired
    DistrictProvinceMapper districtProvinceMapper;
    @Autowired
    DistrictCityMapper districtCityMapper;
    @Autowired
    IdentInspectorMapper identInspectorMapper;
    //查询所有省
    @Override
    public List<DistrictProvince> selectProvince() {
        QueryWrapper queryWrapper=new QueryWrapper<DistrictProvince>();
        return districtProvinceMapper.selectList(queryWrapper);
    }

    //根据省的ID查询城市
    @Override
    public List<DistrictCity> selectCityBuId(String cid) {
        QueryWrapper queryWrapper=new QueryWrapper<DistrictCity>();
        queryWrapper.eq("ci_cid",cid);
        return districtCityMapper.selectList(queryWrapper);
    }
    //根据id查询省
    @Override
    public String selectPrById(String id){
        QueryWrapper queryWrapper=new QueryWrapper<DistrictProvince>();
        queryWrapper.eq("pr_id",id);
        return districtProvinceMapper.selectOne(queryWrapper).getPrName();
    }

    @Override
    //查询前7条网格员信息
    public List<IdentInspector> seleceSeven(){
        QueryWrapper queryWrapper=new QueryWrapper<IdentInspector>();
        queryWrapper.last("limit 1,7");
        return identInspectorMapper.selectList(queryWrapper);
    }
}
