package com.neu.grid.service;

import com.neu.grid.dto.IdentInspectorDTO;
import com.neu.grid.entity.LoginInspector;

public interface GridLoginService {
    public LoginInspector loginInspector(String account,String pass);
    public IdentInspectorDTO personalById(Integer cid);
}
