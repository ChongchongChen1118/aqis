package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.IdentInspectorDTO;
import com.neu.admin.dto.LoginInspectorDTO;
import com.neu.admin.entity.IdentInspector;
import com.neu.admin.entity.LoginInspector;
import com.neu.admin.mapper.AdminIdentInspectorMapper;
import com.neu.admin.mapper.AdminLoginInspectorMapper;
import com.neu.admin.service.AdminProvinceService;
import com.neu.admin.service.AdminCityService;
import com.neu.admin.service.AdminInspectorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminInspectorServiceImpl implements AdminInspectorService {
    @Autowired
    AdminIdentInspectorMapper adminIdentInspectorMapper;
    @Autowired
    AdminLoginInspectorMapper adminLoginInspectorMapper;
    @Autowired
    AdminCityService adminCityService;
    @Autowired
    AdminProvinceService adminProvinceService;
    @Override
    public PageInfo<LoginInspectorDTO> getInspectorList(Integer pageNum) {
        Page<LoginInspector> page=PageHelper.startPage(pageNum,12);
        List<LoginInspector> list= adminLoginInspectorMapper.selectList(null);
        PageInfo<LoginInspector> info=new PageInfo<>(list,5);
//        List<LoginInspectorDTO> loginInspectorDTOList = list.stream().map(loginInspector->
//                getInspectorUserById(loginInspector.getInId())).collect(Collectors.toList());
//        System.out.println(loginInspectorDTOList.size());
        PageInfo<LoginInspectorDTO> pageInfo = page.toPageInfo(loginInspector ->
             getInspectorUserById(loginInspector.getInId())
        );
       /* PageInfo <LoginInspectorDTO> inspectorDTOPageInfo=new PageInfo<>(pageInfo,5);*/
        return pageInfo;
    }

    @Override
    public IdentInspector getInspectorById(Integer id) {
        return null;
    }

    @Override
    public List<IdentInspector> getIdentInsList() {
        List<IdentInspector> inspectorList= adminIdentInspectorMapper.selectList(null);
        return inspectorList;
    }


    public LoginInspectorDTO getInspectorUserById(Integer id) {
        QueryWrapper<LoginInspector> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("in_id",id);
        LoginInspector loginInspector = adminLoginInspectorMapper.selectOne(queryWrapper);
        LoginInspectorDTO loginInspectorDTO = new LoginInspectorDTO();
        BeanUtils.copyProperties(loginInspector, loginInspectorDTO);
        loginInspectorDTO.setIdentInspectorList(getIdentInspectorById(id));
        return loginInspectorDTO;
    }

    public IdentInspectorDTO getIdentInspectorById(Integer id) {
        IdentInspectorDTO identInspectorDTO = new IdentInspectorDTO();
        QueryWrapper<IdentInspector> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ins_cid",id);
        IdentInspector identInspector = adminIdentInspectorMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(identInspector, identInspectorDTO);
        return identInspectorDTO;
    }
    @Override
    public LoginInspector getLoginInspector(String account){
        QueryWrapper queryWrapper=new QueryWrapper<LoginInspector>();
        queryWrapper.eq("in_account",account);
        LoginInspector loginInspector=adminLoginInspectorMapper.selectOne(queryWrapper);
        return loginInspector;
    }

    @Override
    public int setLoginInspector(LoginInspector loginInspector){
        return adminLoginInspectorMapper.insert(loginInspector);
    }

    @Override
    public int setIdentInspector(IdentInspector identInspector){
        return adminIdentInspectorMapper.insert(identInspector);
    }
}
