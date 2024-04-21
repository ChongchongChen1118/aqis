package com.neu.users.service;

import com.github.pagehelper.PageInfo;
import com.neu.users.dto.CityInformationDTO;

public interface AllCity {
    public PageInfo<CityInformationDTO> selectAll(Integer pageNum);
    public PageInfo<CityInformationDTO> selectById(Integer pageNum,String id);
}
