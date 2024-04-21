package com.neu.admin.controller;

import com.neu.admin.entity.DistrictProvince;
import com.neu.admin.entity.VerifyAdmin;
import com.neu.admin.mapper.AdminDistrictProvinceMapper;
import com.neu.admin.service.AdminIdeaWorkoutService;
import com.neu.admin.service.AdminProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CountController {
    @Autowired
    AdminIdeaWorkoutService adminIdeaWorkoutService;
    @Autowired
    AdminProvinceService adminProvinceService;
    @RequestMapping("/count-aqi")
    public String countaqi(Model model, HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        Long aqi1=adminIdeaWorkoutService.selectAQI1();
        Long aqi2=adminIdeaWorkoutService.selectAQI2();
        Long aqi3=adminIdeaWorkoutService.selectAQI3();
        Long aqi4=adminIdeaWorkoutService.selectAQI4();
        Long aqi5=adminIdeaWorkoutService.selectAQI5();
        Long aqi6=adminIdeaWorkoutService.selectAQI6();
        model.addAttribute("aqi1",aqi1);
        model.addAttribute("aqi2",aqi2);
        model.addAttribute("aqi3",aqi3);
        model.addAttribute("aqi4",aqi4);
        model.addAttribute("aqi5",aqi5);
        model.addAttribute("aqi6",aqi6);
        return "/admin/count/count-AQI";
    }
    @RequestMapping("/count-index")
    public String toIndex(HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
       return "/admin/count/count-index";
    }

    @RequestMapping("/aqiover/{id}")
    public String selectOverById(@PathVariable("id") Integer id,Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        List<DistrictProvince> list=adminProvinceService.getAllProvince();
        Long a=adminIdeaWorkoutService.selectOverById(id);
        DistrictProvince districtProvince=adminProvinceService.getProvinceById(id);
        model.addAttribute("list",list);
        model.addAttribute("count",a);
        model.addAttribute("name",districtProvince.getPrName());
        return "/admin/count/count-province";
    }

    @RequestMapping("/month")
    public String month(Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        model.addAttribute("month1",adminIdeaWorkoutService.selectMonth1());
        model.addAttribute("month2",adminIdeaWorkoutService.selectMonth2());
        model.addAttribute("month3",adminIdeaWorkoutService.selectMonth3());
        model.addAttribute("month4",adminIdeaWorkoutService.selectMonth4());
        model.addAttribute("month5",adminIdeaWorkoutService.selectMonth5());
        model.addAttribute("month6",adminIdeaWorkoutService.selectMonth6());
        model.addAttribute("month7",adminIdeaWorkoutService.selectMonth7());
        model.addAttribute("month8",adminIdeaWorkoutService.selectMonth8());
        model.addAttribute("month9",adminIdeaWorkoutService.selectMonth9());
        model.addAttribute("month10",adminIdeaWorkoutService.selectMonth10());
        return "/admin/count/count-month";
    }
}
