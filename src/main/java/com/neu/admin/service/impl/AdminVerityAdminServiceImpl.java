package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.admin.mapper.AdminVerityAdminMapper;
import com.neu.admin.service.AdminVerityAdminService;
import com.neu.admin.entity.VerifyAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminVerityAdminServiceImpl implements AdminVerityAdminService {
    @Autowired
    AdminVerityAdminMapper adminVerityAdminMapper;

    @Override
    public List<VerifyAdmin> getAdminList(VerifyAdmin admin) {
        QueryWrapper<VerifyAdmin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ad_account",admin.getAdAccount());
        queryWrapper.eq("ad_pass",admin.getAdPass());
        List<VerifyAdmin> verifyAdminList= adminVerityAdminMapper.selectList(queryWrapper);
        return verifyAdminList;
    }
}
