package com.neu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.admin.dto.BoardDataDTO;
import com.neu.admin.entity.Idea;
import com.neu.admin.mapper.AdminIdeaMapper;
import com.neu.admin.service.AdminBoardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBoardDataServiceImpl implements AdminBoardDataService {
    @Autowired
    AdminIdeaMapper adminIdeaMapper;
    @Override
    public BoardDataDTO getBoardData() {
        BoardDataDTO boardData=new BoardDataDTO();
        boardData.setIdeaNumber(adminIdeaMapper.selectCount(null));
        boardData.setDealIdeaNum(adminIdeaMapper.selectCount(new QueryWrapper<Idea>().eq("id_out",1)));
        boardData.setWaitInsToTestNum(adminIdeaMapper.selectCount(new QueryWrapper<Idea>().eq("id_out",0).ne("id_inspector",0)));
        boardData.setWaitForGiveInsNum(adminIdeaMapper.selectCount(new QueryWrapper<Idea>().eq("id_inspector",0)));
        return boardData;
    }
}
