package com.neu.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.users.dto.CityInformationDTO;
import com.neu.users.entity.CityInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityInformationMapper extends BaseMapper<CityInformation> {
    //查询所有城市空气质量
    public List<CityInformationDTO> selectAll();
    public List<CityInformationDTO> selectById(String id);
}
