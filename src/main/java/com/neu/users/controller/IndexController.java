package com.neu.users.controller;

import com.neu.users.dto.IdeaWorkoutDTO;
import com.neu.users.entity.*;
import com.neu.users.service.AqiService;
import com.neu.users.service.DistrictProvinceService;
import com.neu.users.service.GptService;
import com.neu.users.service.IdeaService;
import com.neu.users.service.Impl.lanya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class IndexController {
    @Autowired
    DistrictProvinceService districtProvinceService;
    @Autowired
    AqiService aqiService;
    @Autowired
    IdeaService ideaService;
    @Autowired
    lanya lanya1;


    @RequestMapping(value = {"/",""})
    public String index(Model model){
        List<IdeaWorkoutDTO> list=ideaService.selectWorkoutLimit();
        List<IdentInspector> list1=districtProvinceService.seleceSeven();
        model.addAttribute("list",list);
        model.addAttribute("list11",list1);
        return "users/user/index";
    }

    @RequestMapping("/feedback/{cid}")
    public String feedback(Model model,@PathVariable("cid") String cid,HttpSession httpSession){
        LoginUser loginUser=(LoginUser) httpSession.getAttribute("user");
        if(loginUser==null){
            return "forward:/user/login";
        }
        List<DistrictProvince> list=districtProvinceService.selectProvince();
        List<Aqi> list1=aqiService.selectAqi();
        List<DistrictCity> list2=districtProvinceService.selectCityBuId(cid);
        model.addAttribute("province",list);
        model.addAttribute("aqi",list1);
        model.addAttribute("city",list2);
        int cid1=Integer.parseInt(cid);
        model.addAttribute("cid1",cid1);
        return "users/user/feedback";
    }

    @RequestMapping("/feedbackjudgment")
    public String feedbackjudgment(Idea idea, Model model, MultipartFile file1, String cid) throws IOException {
        if(idea.getIdCity().equals("")||idea.getIdAqi().equals("")||idea.getIdRemark().equals("")){
            model.addAttribute("judgment",0);
            model.addAttribute(idea);
            return "forward:/user/feedback/"+cid;
        }

        String fileName = file1.getOriginalFilename();
        //处理文件重名问题
        String hzName;
        try {
            hzName = fileName.substring(fileName.lastIndexOf("."));
        }catch (Exception e){
            model.addAttribute("judgment",0);
            model.addAttribute(idea);
            return "forward:/user/feedback/"+cid;
        }
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中file目录的路径
        ClassLoader classLoader = IndexController.class.getClassLoader();
        String targetPath = classLoader.getResource("").getPath();
        String photoPath = targetPath+ "static/users/image";
        System.out.println(photoPath);
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + "/"+fileName;
        //实现上传功能
        file1.transferTo(new File(finalPath));
        idea.setIdPhoto("/users/image/"+fileName);
        idea.setIdDate(new Date());
        idea.setIdInspector(0);
        idea.setIdOut(0);
        ideaService.insertIdea(idea);
        model.addAttribute("judgment",1);
        ideaService.quantity(idea.getIdUser());
        return "forward:/user/feedback/1";
    }

    @RequestMapping("/placard")
    public String placard(){
        return "users/user/propaganda";
    }

    @RequestMapping("/shishi")
    public String shishi(Model model){
        List<Lanya> list=ideaService.seleceshishi();
        model.addAttribute("list1",list);
        return "users/user/shishi";
    }
}
