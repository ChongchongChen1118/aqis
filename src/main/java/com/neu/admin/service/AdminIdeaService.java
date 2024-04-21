package com.neu.admin.service;

import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.IdeaDTO;
import com.neu.admin.entity.Idea;

import java.util.List;

public interface AdminIdeaService {
    PageInfo<IdeaDTO> getIdeaList(Integer pageNum);

    int updateIdeaIns(Idea idea);

    Idea getIdeaOneById(Integer id);

    List<Idea> getIdeaList();

    PageInfo<IdeaDTO> getIdeaNoGaveList(Integer pageNum);

    public PageInfo<IdeaDTO> getIdeaGaveList(Integer pageNum);

    PageInfo<IdeaDTO> searchIdea(String keyword);

}
