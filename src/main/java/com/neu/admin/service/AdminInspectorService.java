package com.neu.admin.service;

import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.LoginInspectorDTO;
import com.neu.admin.entity.IdentInspector;
import com.neu.admin.entity.LoginInspector;

import java.util.List;

public interface AdminInspectorService {
    public PageInfo<LoginInspectorDTO> getInspectorList(Integer pageNum);
    public IdentInspector getInspectorById(Integer id);
    public List<IdentInspector> getIdentInsList();
    public LoginInspector getLoginInspector(String account);
    public int setLoginInspector(LoginInspector loginInspector);
    public int setIdentInspector(IdentInspector identInspector);
}
