package com.neu.grid.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.grid.entity.CityInformation;
import com.neu.grid.mapper.GridCityInformationMapper;
import com.neu.grid.service.GridCityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridGridCityInformationServiceImpl implements GridCityInformationService {
    @Autowired
    GridCityInformationMapper gridCityInformationMapper;
    @Override
    public List<CityInformation> selectCityById(Integer id){
        QueryWrapper queryWrapper=new QueryWrapper<CityInformation>();
        queryWrapper.eq("inf_cid",id);
        return gridCityInformationMapper.selectList(queryWrapper);
    }
    @Override
    public int update(CityInformation cityInformation){
        QueryWrapper queryWrapper=new QueryWrapper<CityInformation>();
        queryWrapper.eq("inf_cid",cityInformation.getInfCid());
        return gridCityInformationMapper.update(cityInformation,queryWrapper);
    }

    @Override
    public int insert(CityInformation cityInformation){
        return gridCityInformationMapper.insert(cityInformation);
    }
}
