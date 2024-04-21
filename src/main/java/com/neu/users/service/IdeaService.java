package com.neu.users.service;

import com.github.pagehelper.PageInfo;
import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.Idea;
import com.neu.users.entity.Lanya;

import java.util.List;

public interface IdeaService {
    public int insertIdea(Idea idea);
    public void quantity(Integer id);
    public PageInfo<IdeaWorkoutDTO> selectAllWorkout(Integer pageNum);
    public PageInfo<IdeaWorkoutDTO> selectWorkoutById(Integer pageNum,Integer id);
    public List<IdeaWorkoutDTO> selectWorkoutLimit();
    public List<Lanya> seleceshishi();
}
