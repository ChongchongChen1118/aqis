package com.neu.admin.service;

import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.LoginUserDTO;

public interface AdminUserService {
    public PageInfo<LoginUserDTO> getLoginUserList(Integer pageNum);
    public int deleteUser(Integer userId);
    public int blockedUser(Integer id,Integer deal);

}
