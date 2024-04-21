package com.neu.users.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.users.entity.IdentUser;
import com.neu.users.entity.LoginUser;
import com.neu.users.mapper.IdentUserMapper;
import com.neu.users.mapper.LoginMapper;
import com.neu.users.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    IdentUserMapper identUserMapper;

    @Override
    //注册账号
    public int signIn(String account,String pass){
        Date date=new Date();
        LoginUser loginUser=LoginUser.builder()
                .usAccount(account)
                .usPass(pass)
                .usDate(date)
                .usOut(0)
                .build();
        return loginMapper.insert(loginUser);
    }

    @Override
    //查询账号
    public List<LoginUser> selectAccount(String account){
        QueryWrapper queryWrapper=new QueryWrapper<LoginUser>();
        queryWrapper.eq("us_account",account);
        List<LoginUser> list=loginMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public IdentUser selectIdentByid(Integer id) {
        QueryWrapper queryWrapper=new QueryWrapper<IdentUser>();
        queryWrapper.eq("use_cid",id);
        IdentUser identUser= identUserMapper.selectOne(queryWrapper);
        return identUser;
    }

    @Override
    public int updateIdent(IdentUser identUser){
        QueryWrapper queryWrapper=new QueryWrapper<IdentUser>();
        queryWrapper.eq("use_cid",identUser.getUseCid());
        int a=identUserMapper.update(identUser,queryWrapper);
        return a;
    }
}
