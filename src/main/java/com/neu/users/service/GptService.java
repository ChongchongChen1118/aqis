package com.neu.users.service;

import com.github.pagehelper.PageInfo;
import com.neu.users.entity.Idea;

public interface GptService {
    public String getGpt(String url,String body,String resultName);
}
