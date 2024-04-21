package com.neu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.admin.entity.IdeaWorkout;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface AdminIdeaWorkoutMapper extends BaseMapper<IdeaWorkout> {
  public Long selectOverById(Integer id);
  public Long selectNow1(Date date);
  public Long selectNow2(Date dateBefore,Date After);
}
