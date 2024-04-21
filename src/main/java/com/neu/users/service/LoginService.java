package com.neu.users.service;

import com.neu.users.entity.IdentUser;
import com.neu.users.entity.LoginUser;

import java.util.List;

public interface LoginService {
    public int signIn(String account,String pass);
    public List<LoginUser> selectAccount(String account);
    public IdentUser selectIdentByid(Integer id);
    public int updateIdent(IdentUser identUser);
}
