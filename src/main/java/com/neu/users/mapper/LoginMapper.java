package com.neu.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.users.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<LoginUser> {
}
