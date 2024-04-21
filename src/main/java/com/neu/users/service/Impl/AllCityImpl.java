package com.neu.users.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.users.dto.CityInformationDTO;
import com.neu.users.mapper.CityInformationMapper;
import com.neu.users.service.AllCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AllCityImpl implements AllCity {
    @Autowired
    CityInformationMapper cityInformationMapper;

    @Override
    public PageInfo<CityInformationDTO> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<CityInformationDTO> list=cityInformationMapper.selectAll();
        PageInfo<CityInformationDTO> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public PageInfo<CityInformationDTO> selectById(Integer pageNum,String id){
        PageHelper.startPage(pageNum, 8);
        List<CityInformationDTO> list=cityInformationMapper.selectById(id);
        PageInfo<CityInformationDTO> page = new PageInfo<>(list, 5);
        return page;
    }
}
