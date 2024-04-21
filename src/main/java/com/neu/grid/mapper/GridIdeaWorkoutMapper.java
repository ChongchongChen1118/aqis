package com.neu.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.grid.dto.GridIdeaWorkoutDTO;
import com.neu.grid.entity.IdeaWorkout;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GridIdeaWorkoutMapper extends BaseMapper<IdeaWorkout> {
    public List<GridIdeaWorkoutDTO> selectAllWorkout(Integer id);
}
