package com.neu.grid.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.grid.dto.IdentInspectorDTO;
import com.neu.grid.entity.LoginInspector;
import com.neu.grid.mapper.GridIdentInspectorMapper;
import com.neu.grid.mapper.GridLoginInspectorMapper;
import com.neu.grid.service.GridLoginService;
import com.neu.users.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridGridLoginServiceImpl implements GridLoginService {
    @Autowired
    GridLoginInspectorMapper gridLoginInspectorMapper;
    @Autowired
    GridIdentInspectorMapper gridIdentInspectorMapper;

    @Override
    public LoginInspector loginInspector(String account,String pass) {
        QueryWrapper queryWrapper=new QueryWrapper<LoginUser>();
        queryWrapper.eq("in_account",account);
        LoginInspector loginInspector= gridLoginInspectorMapper.selectOne(queryWrapper);
        return loginInspector;
    }

    @Override
    public IdentInspectorDTO personalById(Integer cid){
        IdentInspectorDTO identInspectorDTO=gridIdentInspectorMapper.personalById(cid);
        return identInspectorDTO;
    }
}
