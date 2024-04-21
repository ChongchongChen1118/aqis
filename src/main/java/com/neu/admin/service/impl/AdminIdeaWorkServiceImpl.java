package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.IdeaDTO;
import com.neu.admin.entity.*;
import com.neu.admin.mapper.*;
import com.neu.admin.service.AdminIdeaWorkoutService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminIdeaWorkServiceImpl implements AdminIdeaWorkoutService {
    @Autowired
    AdminIdeaMapper adminIdeaMapper;
    @Autowired
    AdminIdentInspectorMapper adminIdentInspectorMapper;
    @Autowired
    AdminDistrictCityMapper adminDistrictCityMapper;
    @Autowired
    AdminDistrictProvinceMapper adminDistrictProvinceMapper;
    @Autowired
    AdminIdentUserMapper adminIdentUserMapperUser;
    @Autowired
    AdminAqiMapper adminAqiMapper;
    @Autowired
    AdminIdeaWorkoutMapper adminIdeaWorkoutMapper;



    @Override
    public PageInfo<IdeaDTO> getIdeaWorkedList(Integer pageNum) {
        Page<Idea> pageIdea=PageHelper.startPage(pageNum,10);
        QueryWrapper<Idea> queryWrapper = new QueryWrapper<Idea>();
        queryWrapper.eq("id_out", 1);
        queryWrapper.orderByDesc("id_date");
        List<Idea> list= adminIdeaMapper.selectList(queryWrapper);
        PageInfo<IdeaDTO> pageInfo = pageIdea.toPageInfo(idea -> {
            System.out.println(idea.getIdId());
            return getIdeaById(idea.getIdId());
        });
        /*List<IdeaDTO> ideaList = ideaMapper.selectList(queryWrapper).stream().map(idea ->
                getIdeaById(idea.getIdId())).collect(Collectors.toList());*/
        /*PageInfo<IdeaDTO> info = new PageInfo<>(ideaList);*/
        return pageInfo;

    }
    @Override
    public PageInfo<IdeaDTO> searchIdea(String keyword) {
        QueryWrapper<DistrictCity> cityQueryWrapper = new QueryWrapper<>();
        cityQueryWrapper.like("ci_name", keyword);
        List<DistrictCity> districtCity = adminDistrictCityMapper.selectList(cityQueryWrapper);

        QueryWrapper<Idea> queryWrapper = new QueryWrapper<Idea>();
        queryWrapper.like("id_inspector", 0);

        List<Idea> ideaList = adminIdeaMapper.selectList(queryWrapper);

        List<IdeaDTO> ideaDTOList = ideaList.stream().map(idea -> {
            return getIdeaById(idea.getIdId());
        }).collect(Collectors.toList());

        List<IdeaDTO> ideaDTOS=new ArrayList<>();
        for (DistrictCity city: districtCity) {
            for (IdeaDTO ideaDTO:ideaDTOList) {
                if(city.getCiId().equals(ideaDTO.getIdCity())){
                    ideaDTOS.add(ideaDTO);
                }
            }

        }
        PageInfo<IdeaDTO> info=new PageInfo<>(ideaDTOS);

        return info;
    }
    public IdeaDTO getIdeaById(Integer id) {
        QueryWrapper<Idea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_id", id);
        Idea idea = adminIdeaMapper.selectOne(queryWrapper);

        IdeaDTO ideaDTO = new IdeaDTO();
        BeanUtils.copyProperties(idea, ideaDTO);

        QueryWrapper<DistrictCity> cityQueryWrapper = new QueryWrapper<>();
        cityQueryWrapper.eq("ci_id", idea.getIdCity());
        DistrictCity districtCity = adminDistrictCityMapper.selectOne(cityQueryWrapper);

        ideaDTO.setDistrictCity(districtCity);
        ideaDTO.setDistrictProvince(getProvinceById(districtCity.getCiCid()));
        ideaDTO.setIdentInspector(getIdentInspectorById(idea.getIdInspector()));
        ideaDTO.setAqi(getAqiById(idea.getIdAqi()));
        ideaDTO.setIdentUser(getIdentUserById(idea.getIdUser()));
        ideaDTO.setIdeaWorkout(getIdeaWork(idea.getIdId()));
        System.out.println(getWorkedAqiById(getIdeaWork(idea.getIdId()).getWoAqi()));
        ideaDTO.setWorkedAqi(getWorkedAqiById(getIdeaWork(idea.getIdId()).getWoAqi()));

        return ideaDTO;
    }


    @Override
    public Idea getIdeaOneById(Integer id) {
        QueryWrapper<Idea> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id_id", id);
        Idea idea = adminIdeaMapper.selectOne(queryWrapper);
        return idea;
    }

    @Override
    public List<Idea> getIdeaList() {
        List<Idea> ideaList = adminIdeaMapper.selectList(null);
        return ideaList;
    }




    private DistrictProvince getProvinceById(Integer provinceId) {
        QueryWrapper<DistrictProvince> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pr_id", provinceId);
        DistrictProvince districtProvince = adminDistrictProvinceMapper.selectOne(queryWrapper);
        return districtProvince;
    }

    public IdentInspector getIdentInspectorById(Integer id) {
        QueryWrapper<IdentInspector> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ins_id", id);
        IdentInspector identInspector = adminIdentInspectorMapper.selectOne(queryWrapper);
        return identInspector;
    }

    public Aqi getAqiById(Integer id) {
        QueryWrapper<Aqi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aq_id", id);
        Aqi aqi = adminAqiMapper.selectOne(queryWrapper);
        return aqi;
    }
    public Aqi getWorkedAqiById(Integer id) {
        QueryWrapper<Aqi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aq_id", id);
        Aqi aqi = adminAqiMapper.selectOne(queryWrapper);
        return aqi;
    }

    public IdentUser getIdentUserById(Integer id) {
        QueryWrapper<IdentUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("use_cid", id);
        IdentUser identUser = adminIdentUserMapperUser.selectOne(queryWrapper);
        return identUser;
    }

    public IdeaWorkout getIdeaWork(Integer id) {
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_cid", id);
        IdeaWorkout ideaWorkout = adminIdeaWorkoutMapper.selectOne(queryWrapper);
        return ideaWorkout;
    }
    @Override
    public Long selectAQI1(){
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_aqi", 1);
        return adminIdeaWorkoutMapper.selectCount(queryWrapper);
    }
    @Override
    public Long selectAQI2(){
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_aqi", 2);
        return adminIdeaWorkoutMapper.selectCount(queryWrapper);
    }
    @Override
    public Long selectAQI3(){
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_aqi", 3);
        return adminIdeaWorkoutMapper.selectCount(queryWrapper);
    }
    @Override
    public Long selectAQI4(){
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_aqi", 4);
        return adminIdeaWorkoutMapper.selectCount(queryWrapper);
    }
    @Override
    public Long selectAQI5(){
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_aqi", 5);
        return adminIdeaWorkoutMapper.selectCount(queryWrapper);
    }
    @Override
    public Long selectAQI6(){
        QueryWrapper<IdeaWorkout> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wo_aqi", 6);
        return adminIdeaWorkoutMapper.selectCount(queryWrapper);
    }

    @Override
    public Long selectOverById(Integer id){
        return adminIdeaWorkoutMapper.selectOverById(id);
    }

    @Override
    public Long selectMonth1(){
        Date date=new Date();
        long time=date.getTime();
        long a=2592000*1000000;
        date.setTime(time-a);
        return adminIdeaWorkoutMapper.selectNow1(date);
    }
    @Override
    public Long selectMonth2(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*2);
        date3.setTime(time-a);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth3(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*3);
        date3.setTime(time-a*2);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth4(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*4);
        date3.setTime(time-a*3);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth5(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*5);
        date3.setTime(time-a*4);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth6(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*6);
        date3.setTime(time-a*5);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth7(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*7);
        date3.setTime(time-a*6);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth8(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*8);
        date3.setTime(time-a*7);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth9(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*9);
        date3.setTime(time-a*8);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
    @Override
    public Long selectMonth10(){
        Date date1=new Date();
        Date date2=new Date();
        Date date3=new Date();
        long time=date1.getTime();
        long a=2592000*1000000;
        date2.setTime(time-a*10);
        date3.setTime(time-a*9);
        return adminIdeaWorkoutMapper.selectNow2(date2,date3);
    }
}
