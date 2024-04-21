package com.neu.admin.service;

import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.IdeaDTO;
import com.neu.admin.entity.Idea;

import java.util.List;

public interface AdminIdeaWorkoutService {


    PageInfo<IdeaDTO> getIdeaWorkedList(Integer page);
    Idea getIdeaOneById(Integer id);

    List<Idea> getIdeaList();

    PageInfo<IdeaDTO> searchIdea(String keyword);
    public Long selectAQI1();
    public Long selectAQI2();
    public Long selectAQI3();
    public Long selectAQI4();
    public Long selectAQI5();
    public Long selectAQI6();
    public Long selectOverById(Integer id);
    public Long selectMonth1();
    public Long selectMonth2();
    public Long selectMonth3();
    public Long selectMonth4();
    public Long selectMonth5();
    public Long selectMonth6();
    public Long selectMonth7();
    public Long selectMonth8();
    public Long selectMonth9();
    public Long selectMonth10();
}
