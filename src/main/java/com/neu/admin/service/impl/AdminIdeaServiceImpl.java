package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.IdeaDTO;
import com.neu.admin.entity.*;
import com.neu.admin.mapper.*;
import com.neu.admin.service.AdminIdeaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminIdeaServiceImpl implements AdminIdeaService {
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
    public PageInfo<IdeaDTO> getIdeaList(Integer pageNum) {
        Page<Idea> pageIdea=PageHelper.startPage(pageNum,10);
        QueryWrapper<Idea> queryWrapper = new QueryWrapper<Idea>();
        queryWrapper.eq("id_out", 0);
        queryWrapper.orderByAsc( "id_inspector").orderByDesc("id_aqi", "id_date");
        List<Idea> list= adminIdeaMapper.selectList(queryWrapper);
        PageInfo<IdeaDTO> pageInfo = pageIdea.toPageInfo(idea -> {
            return getIdeaById(idea.getIdId());
        });
        return pageInfo;

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
        return ideaDTO;
    }
    @Override
    public PageInfo<IdeaDTO> getIdeaNoGaveList(Integer pageNum) {
        Page<Idea> pageIdea=PageHelper.startPage(pageNum,10);
        QueryWrapper<Idea> queryWrapper = new QueryWrapper<Idea>();
        queryWrapper.eq("id_inspector", 0);
        queryWrapper.orderByDesc("id_aqi", "id_date");
        List<Idea> list= adminIdeaMapper.selectList(queryWrapper);
        PageInfo<IdeaDTO> pageInfo = pageIdea.toPageInfo(idea -> {
            return getIdeaById(idea.getIdId());
        });
        return pageInfo;

    }
    @Override
    public PageInfo<IdeaDTO> getIdeaGaveList(Integer pageNum) {
        Page<Idea> pageIdea=PageHelper.startPage(pageNum,10);
        QueryWrapper<Idea> queryWrapper = new QueryWrapper<Idea>();
        queryWrapper.eq("id_out", 0);
        queryWrapper.ne("id_inspector", 0);
        queryWrapper.orderByDesc("id_aqi", "id_date");
        List<Idea> list= adminIdeaMapper.selectList(queryWrapper);
        PageInfo<IdeaDTO> pageInfo = pageIdea.toPageInfo(idea -> {
            return getIdeaById(idea.getIdId());
        });
        return pageInfo;

    }


    @Override
    public PageInfo<IdeaDTO> searchIdea(String keyword) {
        QueryWrapper<DistrictCity> cityQueryWrapper = new QueryWrapper<>();
        cityQueryWrapper.like("ci_name", keyword);
        List<DistrictCity> districtCity = adminDistrictCityMapper.selectList(cityQueryWrapper);

        QueryWrapper<Idea> queryWrapper = new QueryWrapper<Idea>();
        queryWrapper.eq("id_out",0);
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


    @Override
    public int updateIdeaIns(Idea idea) {
        int i = adminIdeaMapper.updateById(idea);
        return i;
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
        queryWrapper.eq("ins_cid", id);
        IdentInspector identInspector = adminIdentInspectorMapper.selectOne(queryWrapper);
        return identInspector;
    }

    public Aqi getAqiById(Integer id) {
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
}
