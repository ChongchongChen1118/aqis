package com.neu.users.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.users.entity.Aqi;
import com.neu.users.mapper.AqiMapper;
import com.neu.users.service.AqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AqiServiceImpl implements AqiService {
    @Autowired
    AqiMapper aqiMapper;

    //查询所有AQI值
    @Override
    public List<Aqi> selectAqi() {
        QueryWrapper queryWrapper=new QueryWrapper<Aqi>();
        return aqiMapper.selectList(queryWrapper);
    }
}
