package com.neu.users.controller;

import com.github.pagehelper.PageInfo;
import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.DistrictProvince;
import com.neu.users.entity.Idea;
import com.neu.users.entity.LoginUser;
import com.neu.users.service.DistrictProvinceService;
import com.neu.users.service.FeedbackService;
import com.neu.users.service.GptService;
import com.neu.users.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    IdeaService ideaService;
    @Autowired
    DistrictProvinceService districtProvinceService;
    @Autowired
    GptService gptService;

    @RequestMapping("/submit/{pageNum}")
    public String submit(@PathVariable("pageNum")String pageNum, Model model, HttpSession httpSession){
        LoginUser loginUser=(LoginUser) httpSession.getAttribute("user");
        if(loginUser==null){
            return "forward:/user/login";
        }
        PageInfo<Idea> pageInfo=feedbackService.selectIdeaByZero(Integer.parseInt(pageNum),loginUser.getUsId());
        model.addAttribute("page",pageInfo);
        return "users/user/submit";
    }
    @RequestMapping("/resolved/{pageNum}")
    public String resolved(@PathVariable("pageNum")String pageNum,Model model,HttpSession httpSession){
        LoginUser loginUser=(LoginUser) httpSession.getAttribute("user");
        if(loginUser==null){
            return "forward:/user/login";
        }
        PageInfo<IdeaWorkoutDTO> pageInfo=feedbackService.resolved(Integer.parseInt(pageNum),loginUser.getUsId());
        model.addAttribute("page",pageInfo);
        return "users/user/resolved";
    }

    @RequestMapping("/evaluate")
    public String evaluate(String ins,String evaluate,Model model,String woId){
        int a=feedbackService.evaluate(Integer.parseInt(evaluate),ins);
        if(a>0) {
            model.addAttribute("records", 1);
            feedbackService.rated(woId);
        }else{
            model.addAttribute("records", 0);
        }
        return "forward:/user/resolved/1";
    }

    @RequestMapping("/urban/{city}/{page}")
    public String selectWorkoutByCity(@PathVariable("page")Integer page,@PathVariable("city")Integer city,Model model){
        PageInfo<IdeaWorkoutDTO> pageInfo=feedbackService.selectWorkoutByCity(city,page);
        Integer select1=feedbackService.select1(city);
        Integer select2=feedbackService.select2(city);
        Integer select3=feedbackService.select3(city);
        Integer select4=feedbackService.select4(city);
        Integer select5=feedbackService.select5(city);
        Integer select6=feedbackService.select6(city);
        model.addAttribute("page",pageInfo);
        model.addAttribute("pageNum",page);
        model.addAttribute("city",city);
        model.addAttribute("select1",select1);
        model.addAttribute("select2",select2);
        model.addAttribute("select3",select3);
        model.addAttribute("select4",select4);
        model.addAttribute("select5",select5);
        model.addAttribute("select6",select6);
        String sheng=pageInfo.getList().get(0).getPrName();
        String shi=pageInfo.getList().get(0).getCiName();
        String a=gptService.getGpt("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=ZFEFvhGYVA7WofE2Ob1ovfA5&client_secret=k7It7Phg8kOf2iQakG7fl9xfN3j9iuPB","{}","access_token");
        String b=gptService.getGpt("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token="+a,"{\"messages\":[{\"role\":\"user\",\"content\":\"请介绍一下"+sheng+shi+"的空气质量，450字左右\"}]}","result");
        model.addAttribute("gpt",b);
        return "users/user/urban";
    }

    @RequestMapping("/allworkout/{page}")
    public String selectAllWorkout(@PathVariable("page")Integer page,Model model){
        PageInfo<IdeaWorkoutDTO> pageInfo=ideaService.selectAllWorkout(page);
        List<DistrictProvince> list=districtProvinceService.selectProvince();
        model.addAttribute("page",pageInfo);
        model.addAttribute("province",list);
        model.addAttribute("judgment",1);
        return "users/user/allworkout";
    }

    @RequestMapping("/idworkout/{city}/{page}")
    public String selectWorkoutById(@PathVariable("page")Integer page,Model model,@PathVariable("city") Integer city){
        PageInfo<IdeaWorkoutDTO> pageInfo=ideaService.selectWorkoutById(page,city);
        List<DistrictProvince> list=districtProvinceService.selectProvince();
        model.addAttribute("page",pageInfo);
        model.addAttribute("province",list);
        model.addAttribute("judgment",2);
        model.addAttribute("city",city);
        model.addAttribute("pageName",districtProvinceService.selectPrById(""+city));
        return "users/user/allworkout";
    }
}
