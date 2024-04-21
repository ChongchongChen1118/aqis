package com.neu.users.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.Idea;
import com.neu.users.entity.IdentUser;
import com.neu.users.entity.Lanya;
import com.neu.users.mapper.IdeaMapper;
import com.neu.users.mapper.IdeaWorkoutMapper;
import com.neu.users.mapper.IdentUserMapper;
import com.neu.users.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaServiceImpl implements IdeaService {
    @Autowired
    IdeaMapper ideaMapper;
    @Autowired
    IdentUserMapper identUserMapper;
    @Autowired
    IdeaWorkoutMapper ideaWorkoutMapper;

    //添加反馈
    @Override
    public int insertIdea(Idea idea) {
        return ideaMapper.insert(idea);
    }
    @Override
    //添加反馈记录
    public void quantity(Integer id){
        QueryWrapper queryWrapper=new QueryWrapper<IdentUser>();
        queryWrapper.eq("use_cid",id);
        IdentUser identUser=identUserMapper.selectOne(queryWrapper);
        int a=identUser.getUseContribution()+1;
        identUser.setUseContribution(a);
        QueryWrapper queryWrapper1=new QueryWrapper<IdentUser>();
        queryWrapper1.eq("use_cid",id);
        identUserMapper.update(identUser,queryWrapper1);
    }

    @Override
    public PageInfo<IdeaWorkoutDTO> selectAllWorkout(Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        QueryWrapper queryWrapper=new QueryWrapper<IdeaWorkoutDTO>();
        List<IdeaWorkoutDTO> list=ideaWorkoutMapper.selectAllWorkout();
        PageInfo<IdeaWorkoutDTO> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public PageInfo<IdeaWorkoutDTO> selectWorkoutById(Integer pageNum,Integer id){
        PageHelper.startPage(pageNum, 8);
        List<IdeaWorkoutDTO> list=ideaWorkoutMapper.selectWorkoutById(id);
        PageInfo<IdeaWorkoutDTO> page=new PageInfo<>(list,5);
        return page;
    }
    @Override
    public List<IdeaWorkoutDTO> selectWorkoutLimit(){
        List<IdeaWorkoutDTO> list=ideaWorkoutMapper.selectWorkoutLimit();
        return list;
    }

    @Override
    public List<Lanya> seleceshishi() {
        return ideaMapper.selectShishi();
    }

}
