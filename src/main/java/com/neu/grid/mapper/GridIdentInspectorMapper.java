package com.neu.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.grid.dto.IdentInspectorDTO;
import com.neu.grid.entity.IdentInspector;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GridIdentInspectorMapper extends BaseMapper<IdentInspector> {
    public IdentInspectorDTO personalById(Integer id);
}
