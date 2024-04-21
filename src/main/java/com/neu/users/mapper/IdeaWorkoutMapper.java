package com.neu.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.IdeaWorkout;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IdeaWorkoutMapper extends BaseMapper<IdeaWorkout> {
    //查询已经解决的意见
    public List<IdeaWorkoutDTO> resolved(int id);
    public List<IdeaWorkoutDTO> selectWorkoutByCity(int id);
    public List<IdeaWorkoutDTO> selectAllWorkout();
    public List<IdeaWorkoutDTO> selectWorkoutById(Integer id);
    public List<IdeaWorkoutDTO> selectWorkoutLimit();
    public Integer select1(Integer id);
    public Integer select2(Integer id);
    public Integer select3(Integer id);
    public Integer select4(Integer id);
    public Integer select5(Integer id);
    public Integer select6(Integer id);
}
