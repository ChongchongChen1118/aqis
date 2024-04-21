package com.neu.users.service;

import com.github.pagehelper.PageInfo;
import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.Idea;

public interface FeedbackService {
    public PageInfo<Idea> selectIdeaByZero(Integer pageNum, Integer id);
    public PageInfo<IdeaWorkoutDTO> resolved(Integer pageNum, Integer id);
    public int evaluate(Integer a,String id);
    public int rated(String id);
    public PageInfo<IdeaWorkoutDTO> selectWorkoutByCity(Integer id,Integer pageNum);
    public Integer select1(Integer id);
    public Integer select2(Integer id);
    public Integer select3(Integer id);
    public Integer select4(Integer id);
    public Integer select5(Integer id);
    public Integer select6(Integer id);
}
