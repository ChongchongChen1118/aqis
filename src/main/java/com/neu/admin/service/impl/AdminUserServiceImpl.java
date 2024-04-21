package com.neu.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.IdentUserDTO;
import com.neu.admin.dto.LoginUserDTO;
import com.neu.admin.entity.LoginUser;
import com.neu.admin.mapper.AdminIdentUserMapper;
import com.neu.admin.mapper.AdminLoginUserMapper;
import com.neu.admin.service.AdminUserService;
import com.neu.admin.entity.IdentUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    AdminLoginUserMapper adminLoginUserMapper;
    @Autowired
    AdminIdentUserMapper adminIdentUserMapper;

    @Override
    public PageInfo<LoginUserDTO> getLoginUserList(Integer pageNum) {
        Page<LoginUser> page = PageHelper.startPage(pageNum, 10);
        QueryWrapper<LoginUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("us_date");//添加封禁账号和正常分开显示
        List<LoginUser> list = adminLoginUserMapper.selectList(queryWrapper);
        PageInfo<LoginUser> info = new PageInfo<>(list, 5);
        PageInfo<LoginUserDTO> pageInfo = page.toPageInfo(loginUser -> {
            return getUserById(loginUser.getUsId());
        });
        return pageInfo;
    }

    @Override
    public int deleteUser(Integer userId) {
        QueryWrapper<IdentUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("use_cid", userId);
        int row1 = adminIdentUserMapper.delete(queryWrapper);
        int row2 = adminLoginUserMapper.deleteById(userId);
        return row1 + row2;
    }

    @Override
    public int blockedUser(Integer id, Integer deal) {
        LoginUser loginUser = adminLoginUserMapper.selectById(id);
        if (deal == 1) {
            loginUser.setUsOut(1);
        } else loginUser.setUsOut(0);

        int row = adminLoginUserMapper.updateById(loginUser);
        return row;
    }

    public LoginUserDTO getUserById(Integer id) {
        QueryWrapper<LoginUser> loginUserQueryWrapper = new QueryWrapper<>();
        loginUserQueryWrapper.eq("us_id", id);
        LoginUser loginUser = adminLoginUserMapper.selectOne(loginUserQueryWrapper);
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        BeanUtils.copyProperties(loginUser, loginUserDTO);
        loginUserDTO.setIdentUserDTO(getIdentUserById(id));
        return loginUserDTO;
    }

    public IdentUserDTO getIdentUserById(Integer id) {
        IdentUserDTO identUserDTO = new IdentUserDTO();
        QueryWrapper<IdentUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("use_cid", id);
        IdentUser identUser = adminIdentUserMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(identUser, identUserDTO);
        return identUserDTO;
    }
}
