package com.neu.users.controller;

import com.github.pagehelper.PageInfo;
import com.neu.users.dto.CityInformationDTO;
import com.neu.users.entity.DistrictProvince;
import com.neu.users.service.AllCity;
import com.neu.users.service.DistrictProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class AllCityController {
    @Autowired
    AllCity allCity;
    @Autowired
    DistrictProvinceService districtProvinceService;

    @RequestMapping("/allcity/{pageNum}")
    public String allcity(@PathVariable("pageNum") Integer pageNum, Model model){
        PageInfo<CityInformationDTO> pageInfo=allCity.selectAll(pageNum);
        List<DistrictProvince> list=districtProvinceService.selectProvince();
        model.addAttribute("page",pageInfo);
        model.addAttribute("province",list);
        model.addAttribute("pageType",1);
        return "users/user/allcitys";
    }

    @RequestMapping("/allcity/{id}/{pageNum}")
    public String selectById(@PathVariable("pageNum") String pageNum,@PathVariable("id") String id,Model model){
        PageInfo<CityInformationDTO> pageInfo=allCity.selectById(Integer.parseInt(pageNum),id);
        List<DistrictProvince> list=districtProvinceService.selectProvince();
        model.addAttribute("page",pageInfo);
        model.addAttribute("province",list);
        model.addAttribute("pageType",2);
        model.addAttribute("pageid",id);
        model.addAttribute("pageName",districtProvinceService.selectPrById(id));
        return "users/user/allcitys";
    }
}
