package com.neu.users.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.Idea;
import com.neu.users.entity.IdeaWorkout;
import com.neu.users.entity.IdentInspector;
import com.neu.users.mapper.IdeaMapper;
import com.neu.users.mapper.IdeaWorkoutMapper;
import com.neu.users.mapper.IdentInspectorMapper;
import com.neu.users.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    IdeaMapper ideaMapper;
    @Autowired
    IdeaWorkoutMapper ideaWorkoutMapper;
    @Autowired
    IdentInspectorMapper identInspectorMapper;

    //查询未解决的反馈
    @Override
    public PageInfo<Idea> selectIdeaByZero(Integer pageNum,Integer id) {
        PageHelper.startPage(pageNum, 8);
        List<Idea> list=ideaMapper.selectIdeaByZero(id);
        PageInfo<Idea> page = new PageInfo<>(list, 5);
        return page;
    }
    @Override
    public PageInfo<IdeaWorkoutDTO> resolved(Integer pageNum,Integer id){
        PageHelper.startPage(pageNum, 8);
        List<IdeaWorkoutDTO> list=ideaWorkoutMapper.resolved(id);
        PageInfo<IdeaWorkoutDTO> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public int evaluate(Integer a,String id) {
        QueryWrapper queryWrapper=new QueryWrapper<IdentInspector>();
        queryWrapper.eq("ins_cid",id);
        IdentInspector identInspector=identInspectorMapper.selectOne(queryWrapper);
        if(a==1) {
            int aa = identInspector.getInsContribution();
            identInspector.setInsContribution(aa+1);
            return identInspectorMapper.update(identInspector,queryWrapper);
        }
        if(a==0){
            int aa = identInspector.getInsNegative();
            identInspector.setInsNegative(aa+1);
            return identInspectorMapper.update(identInspector,queryWrapper);
        }
        return 0;
    }
    //评价
    @Override
    public int rated(String id) {
        QueryWrapper queryWrapper=new QueryWrapper<IdeaWorkout>();
        queryWrapper.eq("wo_id",id);
        IdeaWorkout ideaWorkout=ideaWorkoutMapper.selectOne(queryWrapper);
        ideaWorkout.setWoEvaluate(1);
        return ideaWorkoutMapper.update(ideaWorkout,queryWrapper);
    }
    @Override
    public PageInfo<IdeaWorkoutDTO> selectWorkoutByCity(Integer id,Integer pageNum){
        PageHelper.startPage(pageNum, 8);
        List<IdeaWorkoutDTO> list=ideaWorkoutMapper.selectWorkoutByCity(id);
        PageInfo<IdeaWorkoutDTO> page = new PageInfo<>(list, 5);
        return page;
    }
    @Override
    public Integer select1(Integer id){
        return ideaWorkoutMapper.select1(id);
    }
    @Override
    public Integer select2(Integer id){
        return ideaWorkoutMapper.select2(id);
    }
    @Override
    public Integer select3(Integer id){
        return ideaWorkoutMapper.select3(id);
    }
    @Override
    public Integer select4(Integer id){
        return ideaWorkoutMapper.select4(id);
    }
    @Override
    public Integer select5(Integer id){
        return ideaWorkoutMapper.select5(id);
    }
    @Override
    public Integer select6(Integer id){
        return ideaWorkoutMapper.select6(id);
    }
}
