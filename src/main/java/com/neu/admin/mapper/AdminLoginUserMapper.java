package com.neu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.admin.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminLoginUserMapper extends BaseMapper<LoginUser> {
}
