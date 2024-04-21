package com.neu.grid.service;


import com.github.pagehelper.PageInfo;
import com.neu.grid.dto.GridIdeaWorkoutDTO;
import com.neu.grid.dto.IdeaDTO;
import com.neu.grid.entity.DistrictCity;
import com.neu.grid.entity.Idea;
import com.neu.grid.entity.IdeaWorkout;

public interface GridIdeaService {
    public PageInfo<IdeaDTO> selectIdea(Integer id,Integer pageNum);
    public PageInfo<GridIdeaWorkoutDTO> selectAllWorkout(Integer id, Integer pageNum);
    public Idea selectIdeaById(Integer id);
    public DistrictCity selectCity(Integer id);
    public int insertIdeaWork(IdeaWorkout ideaWorkout);
    public int updateIdeaOne(Integer id);
    public IdeaWorkout selectWorkOutById(Integer id);
    public int updateWorkOut(Integer id,IdeaWorkout ideaWorkout);
}
