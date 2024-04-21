package com.neu.grid.controller;

import com.github.pagehelper.PageInfo;
import com.neu.grid.dto.GridIdeaWorkoutDTO;
import com.neu.grid.dto.IdeaDTO;
import com.neu.grid.entity.*;
import com.neu.grid.service.GridCityInformationService;
import com.neu.grid.service.GridIdeaService;
import com.neu.grid.service.JudgmentService;
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
@RequestMapping("/grid")
public class GridIdeaController {
    @Autowired
    GridIdeaService gridIdeaService;
    @Autowired
    JudgmentService judgmentService;
    @Autowired
    GridCityInformationService gridCityInformationService;


    @RequestMapping("/tobesolved/{pageNum}")
    public String tobesolved(Model model, HttpSession httpSession, @PathVariable("pageNum") Integer pageNum){
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        PageInfo<IdeaDTO> pageInfo=gridIdeaService.selectIdea(loginInspector.getInId(),pageNum);
        model.addAttribute("page",pageInfo);
        return "grid/feature/to-be-solved";
    }

    @RequestMapping("/resolved/{pageNum}")
    public String resolved(Model model,HttpSession httpSession,@PathVariable("pageNum")Integer pageNum){
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        PageInfo<GridIdeaWorkoutDTO> pageInfo=gridIdeaService.selectAllWorkout(loginInspector.getInId(),pageNum);
        model.addAttribute("page",pageInfo);
        return "grid/feature/resolved";
    }

    @RequestMapping("/submit/{id}")
    public String submit(@PathVariable("id")Integer id,Model model,HttpSession httpSession){
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        Idea idea=gridIdeaService.selectIdeaById(id);
        DistrictCity districtCity=gridIdeaService.selectCity(idea.getIdCity());
        model.addAttribute("districtCity",districtCity);
        model.addAttribute("id",id);
        return "grid/feature/submit";
    }

    @RequestMapping("/submithandle")
    public String submithandle(Model model, HttpSession httpSession, MultipartFile file1, IdeaWorkout ideaWorkout) throws IOException {
        Date date=new Date();
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        if(ideaWorkout.getWoCo().equals("")||ideaWorkout.getWoSo().equals("")||ideaWorkout.getWoPm().equals("")){
            model.addAttribute("judgment",0);
            return "forward:/grid/submit/"+ideaWorkout.getWoCid();
        }
        String fileName = file1.getOriginalFilename();
        //处理文件重名问题
        String hzName=null;
        try {
            hzName = fileName.substring(fileName.lastIndexOf("."));
        }catch (Exception e){
            model.addAttribute("judgment",0);
            return "forward:/grid/submit/"+ideaWorkout.getWoCid();
        }
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中file目录的路径
        ClassLoader classLoader = GridIdeaController.class.getClassLoader();
        String targetPath = classLoader.getResource("").getPath();
        String photoPath = targetPath+ "static/grid/image";
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + "/"+fileName;
        //实现上传功能
        file1.transferTo(new File(finalPath));
        ideaWorkout.setWoPhoto("/grid/image/"+fileName);
        double aa;
        double bb;
        double cc;
        try {
            aa=Double.parseDouble(ideaWorkout.getWoSo());
            bb=Double.parseDouble(ideaWorkout.getWoCo());
            cc=Double.parseDouble(ideaWorkout.getWoPm());
        }catch (Exception e){
            model.addAttribute("judgment",2);
            return "forward:/grid/submit/"+ideaWorkout.getWoCid();
        }
        int a=judgmentService.judgmentSO(aa);
        int b=judgmentService.judgmentCO(bb);
        int c=judgmentService.judgmentPM(cc);
        int max=judgmentService.max(a,b,c);
        ideaWorkout.setWoAqi(max);
        ideaWorkout.setWoEvaluate(0);
        ideaWorkout.setWoInsCid(loginInspector.getInId());
        ideaWorkout.setWoDate(date);
        gridIdeaService.insertIdeaWork(ideaWorkout);
        model.addAttribute("judgment",1);
        gridIdeaService.updateIdeaOne(Integer.valueOf(ideaWorkout.getWoCid()));
        Idea idea=gridIdeaService.selectIdeaById(Integer.valueOf(ideaWorkout.getWoCid()));
        CityInformation cityInformation=new CityInformation().builder()
                .infSo(Double.valueOf(ideaWorkout.getWoSo()))
                .infCo(Double.valueOf(ideaWorkout.getWoCo()))
                .infPm(Double.valueOf(ideaWorkout.getWoPm()))
                .infAqi(max)
                .infCid(idea.getIdCity())
                .infDate(date)
                .build();
        List<CityInformation> list= gridCityInformationService.selectCityById(cityInformation.getInfCid());
        if(list.size()==0){
            gridCityInformationService.insert(cityInformation);
        }else {
            gridCityInformationService.update(cityInformation);
        }
        return "forward:/grid/tobesolved/1";
    }

    @RequestMapping("/modify/{id}")
    public String modify(Model model,@PathVariable("id") Integer id,HttpSession httpSession){
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        IdeaWorkout ideaWorkout=gridIdeaService.selectWorkOutById(id);
        Idea idea=gridIdeaService.selectIdeaById(Integer.valueOf(ideaWorkout.getWoCid()));
        DistrictCity districtCity=gridIdeaService.selectCity(idea.getIdCity());
        model.addAttribute("districtCity",districtCity);
        model.addAttribute("workout",ideaWorkout);
        return "grid/feature/modify";
    }

    @RequestMapping("/modifyhandle")
    public String modifyhandle(Model model, HttpSession httpSession, MultipartFile file1, IdeaWorkout ideaWorkout) throws IOException {
        Date date=new Date();
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        if(ideaWorkout.getWoCo().equals("")||ideaWorkout.getWoSo().equals("")||ideaWorkout.getWoPm().equals("")){
            model.addAttribute("judgment",0);
            return "forward:/grid/modify/"+ideaWorkout.getWoId();
        }
        String fileName = file1.getOriginalFilename();
        //处理文件重名问题
        String hzName=null;
        try {
            hzName = fileName.substring(fileName.lastIndexOf("."));
        }catch (Exception e){
            model.addAttribute("judgment",0);
            return "forward:/grid/modify/"+ideaWorkout.getWoId();
        }
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中file目录的路径
        ClassLoader classLoader = GridIdeaController.class.getClassLoader();
        String targetPath = classLoader.getResource("").getPath();
        String photoPath = targetPath+ "static/grid/image";
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + "/"+fileName;
        //实现上传功能
        file1.transferTo(new File(finalPath));
        ideaWorkout.setWoPhoto("/grid/image/"+fileName);
        double aa;
        double bb;
        double cc;
        try {
            aa=Double.parseDouble(ideaWorkout.getWoSo());
            bb=Double.parseDouble(ideaWorkout.getWoCo());
            cc=Double.parseDouble(ideaWorkout.getWoPm());
        }catch (Exception e){
            model.addAttribute("judgment",2);
            return "forward:/grid/modify/"+ideaWorkout.getWoId();
        }
        int a=judgmentService.judgmentSO(aa);
        int b=judgmentService.judgmentCO(bb);
        int c=judgmentService.judgmentPM(cc);
        int max=judgmentService.max(a,b,c);
        ideaWorkout.setWoAqi(max);
        ideaWorkout.setWoEvaluate(0);
        ideaWorkout.setWoInsCid(loginInspector.getInId());
        ideaWorkout.setWoDate(date);
        gridIdeaService.updateWorkOut(Integer.valueOf(ideaWorkout.getWoCid()),ideaWorkout);
        model.addAttribute("judgment",1);
        Idea idea=gridIdeaService.selectIdeaById(Integer.valueOf(ideaWorkout.getWoCid()));
        CityInformation cityInformation=new CityInformation().builder()
                .infSo(Double.valueOf(ideaWorkout.getWoSo()))
                .infCo(Double.valueOf(ideaWorkout.getWoCo()))
                .infPm(Double.valueOf(ideaWorkout.getWoPm()))
                .infAqi(max)
                .infCid(idea.getIdCity())
                .infDate(date)
                .build();
        gridCityInformationService.update(cityInformation);
        gridIdeaService.updateWorkOut(ideaWorkout.getWoId(),ideaWorkout);
        return "forward:/grid/resolved/1";
    }
}
