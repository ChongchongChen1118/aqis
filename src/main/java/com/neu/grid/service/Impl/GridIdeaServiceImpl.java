package com.neu.grid.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.grid.dto.GridIdeaWorkoutDTO;
import com.neu.grid.dto.IdeaDTO;
import com.neu.grid.entity.DistrictCity;
import com.neu.grid.entity.Idea;
import com.neu.grid.entity.IdeaWorkout;
import com.neu.grid.mapper.GridDistrictCityMapper;
import com.neu.grid.mapper.GridIdeaMapper;
import com.neu.grid.mapper.GridIdeaWorkoutMapper;
import com.neu.grid.service.GridIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridIdeaServiceImpl implements GridIdeaService {
    @Autowired
    GridIdeaMapper gridIdeaMapper;
    @Autowired
    GridIdeaWorkoutMapper gridIdeaWorkoutMapper;
    @Autowired
    GridDistrictCityMapper gridDistrictCityMapper;

    @Override
    public PageInfo<IdeaDTO> selectIdea(Integer id,Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<IdeaDTO> list=gridIdeaMapper.selectGridDTO(id);
        PageInfo<IdeaDTO> page = new PageInfo<>(list, 5);
        return page;
    }


    @Override
    public PageInfo<GridIdeaWorkoutDTO> selectAllWorkout(Integer id,Integer pageNum){
        PageHelper.startPage(pageNum, 8);
        List<GridIdeaWorkoutDTO> list=gridIdeaWorkoutMapper.selectAllWorkout(id);
        PageInfo<GridIdeaWorkoutDTO> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public Idea selectIdeaById(Integer id){
        QueryWrapper queryWrapper=new QueryWrapper<Idea>();
        queryWrapper.eq("id_id",id);
        return gridIdeaMapper.selectOne(queryWrapper);
    }

    @Override
    public DistrictCity selectCity(Integer id){
        QueryWrapper queryWrapper=new QueryWrapper<DistrictCity>();
        queryWrapper.eq("ci_id",id);
        return gridDistrictCityMapper.selectOne(queryWrapper);
    }

    @Override
    public int insertIdeaWork(IdeaWorkout ideaWorkout){
        return gridIdeaWorkoutMapper.insert(ideaWorkout);
    }

    @Override
    public int updateIdeaOne(Integer id){
        QueryWrapper queryWrapper=new QueryWrapper<Idea>();
        queryWrapper.eq("id_id",id);
        Idea idea=gridIdeaMapper.selectOne(queryWrapper);
        idea.setIdOut(1);
        return gridIdeaMapper.update(idea,queryWrapper);
    }

    @Override
    public IdeaWorkout selectWorkOutById(Integer id){
        QueryWrapper queryWrapper=new QueryWrapper<IdeaWorkout>();
        queryWrapper.eq("wo_id",id);
        IdeaWorkout ideaWorkout=gridIdeaWorkoutMapper.selectOne(queryWrapper);
        return ideaWorkout;
    }

    @Override
    public int updateWorkOut(Integer id,IdeaWorkout ideaWorkout){
        QueryWrapper queryWrapper=new QueryWrapper<IdeaWorkout>();
        queryWrapper.eq("wo_id",id);
        int a=gridIdeaWorkoutMapper.update(ideaWorkout,queryWrapper);
        return a;
    }
}
