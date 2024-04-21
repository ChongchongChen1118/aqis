package com.neu.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.grid.dto.IdeaDTO;
import com.neu.grid.entity.Idea;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
@Mapper
public interface GridIdeaMapper extends BaseMapper<Idea> {
    public List<IdeaDTO> selectGridDTO(Integer id);
    public void insertLanya(String shuju,Date time);
}
