package com.neu.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.grid.dto.IdentInspectorDTO;
import com.neu.grid.entity.LoginInspector;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GridLoginInspectorMapper extends BaseMapper<LoginInspector> {
    public IdentInspectorDTO personalById(Integer id);
}
