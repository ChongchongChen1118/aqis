package com.neu.admin.controller;

import com.github.pagehelper.PageInfo;
import com.neu.admin.dto.*;
import com.neu.admin.entity.*;
import com.neu.admin.service.*;

import com.neu.users.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class MainController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    AdminInspectorService adminInspectorService;
    @Autowired
    AdminIdeaService adminIdeaService;
    @Autowired
    AdminCityService adminCityService;
    @Autowired
    AdminProvinceService adminProvinceService;
    @Autowired
    AdminIdeaWorkoutService adminIdeaWorkoutService;
    @Autowired
    AdminBoardDataService adminBoardDataService;

    @RequestMapping("")
    public String login(){
        return "/admin/login/admin-login";
    }



    //用户
    @RequestMapping("/manage-user/page/{pageNum}")
    public String manage_user(@PathVariable("pageNum")Integer pageNum,
                              Model model, HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<LoginUserDTO> userList= adminUserService.getLoginUserList(pageNum);
        model.addAttribute("userList",userList);
        return "/admin/users/manage-users";
    }
    //网格员
    @RequestMapping("/manage-AQI/page/{pageNum}")
    public String manage_AQI(@PathVariable("pageNum")Integer page,Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<LoginInspectorDTO> inspectorList= adminInspectorService.getInspectorList(page);
        model.addAttribute("inspectorList",inspectorList);
        return "/admin/inspector/manage-AQI";
    }
    //异常信息
    @RequestMapping("/ideaSearch")
    public String ideaSearch(@RequestParam String name, Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<IdeaDTO> ideaDTOList= adminIdeaService.searchIdea(name);
        model.addAttribute("ideaDTOList",ideaDTOList);
        return "/admin/idea/abnormal-information";
    }
    @RequestMapping("/abnormal-information/page/{pageNum}")
    public String abnormalInformation(@PathVariable("pageNum")Integer pageNum, Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<IdeaDTO> ideaDTOList= adminIdeaService.getIdeaList(pageNum);
        List<IdentInspector>inspectorList= adminInspectorService.getIdentInsList();
        model.addAttribute("inspectorList",inspectorList);
        model.addAttribute("ideaDTOList",ideaDTOList);
        return "/admin/idea/abnormal-information";
    }
    @RequestMapping("/IdeaNoGaveIns/page/{pageNum}")
    public String IdeaNoGaveIns(@PathVariable("pageNum")Integer pageNum, Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<IdeaDTO> ideaDTOList= adminIdeaService.getIdeaNoGaveList(pageNum);
        List<IdentInspector>inspectorList= adminInspectorService.getIdentInsList();
        model.addAttribute("inspectorList",inspectorList);
        model.addAttribute("ideaDTOList",ideaDTOList);

        return "/admin/idea/noGave-information";
    }
    @RequestMapping("/IdeaGaveIns/page/{pageNum}")
    public String IdeaGaveIns(@PathVariable("pageNum")Integer pageNum, Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<IdeaDTO> ideaDTOList= adminIdeaService.getIdeaGaveList(pageNum);
        model.addAttribute("ideaDTOList",ideaDTOList);

        return "/admin/idea/gave-information";
    }
    @RequestMapping("/WorkedIdea/page/{pageNum}")
    public String WorkedIdea(@PathVariable("pageNum")Integer pageNum, Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        PageInfo<IdeaDTO> ideaDTOList= adminIdeaWorkoutService.getIdeaWorkedList(pageNum);
        model.addAttribute("ideaDTOList",ideaDTOList);
        return "/admin/idea/worked-information";
    }
    @PostMapping("/updateInspector")
    public String updateInspector(HandlerIdeaInsDTO handlerIdeaInsDTO,Model model,HttpSession httpSession){
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        Idea idea= adminIdeaService.getIdeaOneById(handlerIdeaInsDTO.getIdId());
        idea.setIdInspector(handlerIdeaInsDTO.getIdUser());
        int row=adminIdeaService.updateIdeaIns(idea);
        model.addAttribute("msg",row);
        return "forward:/admin/abnormal-information/page/1";
    }


    //指派网格员
    /*@RequestMapping ("/updateInspector")
    @ResponseBody
        public Map updateUser(HandlerIdeaInsDTO handlerIdeaInsDTO) throws Exception {
        Idea idea= adminIdeaService.getIdeaOneById(handlerIdeaInsDTO.getIdId());
        idea.setIdInspector(handlerIdeaInsDTO.getIdUser());
        System.out.println(handlerIdeaInsDTO.getIdUser());
        System.out.println(handlerIdeaInsDTO.getIdId());
        System.out.println(handlerIdeaInsDTO.getCityId());
        int row = adminIdeaService.updateIdeaIns(idea);
        Map map=new HashMap<>();
        if(row == 1){
            map.put("result","success");
            map.put("msg","成功指派网格员");
        }else{
            map.put("result","fail");
            map.put("msg","指派网格员失败");
        }
        return map;
    }*/

    @RequestMapping ("/blockedAccount/{userId}/{deal}")
    public String blockedAccount(@PathVariable("userId")Integer userId,@PathVariable("deal")Integer deal,HttpSession httpSession) {
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        int row = adminUserService.blockedUser(userId,deal);
        return "redirect:/admin/manage-user/page/1";
    }

    @RequestMapping ("/admin-index")
    public String adminIndex(Model model,HttpSession httpSession) {
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        BoardDataDTO boardData= adminBoardDataService.getBoardData();
        model.addAttribute("boardData",boardData);
        return "admin/admin-index";
    }
    @RequestMapping ("/new-ins/{id}")
    public String newIns(@PathVariable("id") Integer prId,Model model,HttpSession httpSession) {
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        List<DistrictProvince> provinceList= adminProvinceService.getAllProvince();
        model.addAttribute("province",provinceList);
        List<DistrictCity> cityList = adminCityService.getCityByProvince(prId);
        model.addAttribute("city",cityList);
        model.addAttribute("cid",prId);
        return "admin/inspector/new-ins";
    }

    @RequestMapping("/signinJudgment")
    public String signinJudgment(MultipartFile file1,InspectorDTO inspectorDTO,Model model,String cid,HttpSession httpSession) throws IOException {
        VerifyAdmin loginUser=(VerifyAdmin) httpSession.getAttribute("USER_INFO");
        if(loginUser==null){
            return "redirect:/admin";
        }
        String fileName = file1.getOriginalFilename();
        //处理文件重名问题
        String hzName;
        try {
            hzName = fileName.substring(fileName.lastIndexOf("."));
        }catch (Exception e){
            model.addAttribute("judgment",0);
            model.addAttribute("inspectorDTO",inspectorDTO);
            model.addAttribute("cid",cid);
            return "forward:/admin/new-ins/"+cid;
        }
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中file目录的路径
        ClassLoader classLoader = MainController.class.getClassLoader();
        String targetPath = classLoader.getResource("").getPath();
        String photoPath = targetPath+ "static/photo";
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + "/"+fileName;
        //实现上传功能
        file1.transferTo(new File(finalPath));
        LoginInspector loginInspector=new LoginInspector();
        loginInspector.setInAccount(inspectorDTO.getInAccount());
        loginInspector.setInPass(inspectorDTO.getInPass());
        loginInspector.setInDate(new Date());
        LoginInspector loginInspector1=adminInspectorService.getLoginInspector(loginInspector.getInAccount());
        if(loginInspector1!=null){
            model.addAttribute("judgment",1);
            model.addAttribute("inspectorDTO",inspectorDTO);
            model.addAttribute("cid",cid);
            return "forward:/admin/new-ins/"+cid;
        }
        adminInspectorService.setLoginInspector(loginInspector);
        IdentInspector identInspector=new IdentInspector();
        identInspector.setInsPhoto("/photo/"+fileName);
        identInspector.setInsName(inspectorDTO.getInsName());
        identInspector.setInsPhone(inspectorDTO.getInsPhone());
        identInspector.setInsEmail(inspectorDTO.getInsEmail());
        identInspector.setInsCard(inspectorDTO.getInsCard());
        identInspector.setInsAddress(inspectorDTO.getInsAddress());
        identInspector.setInsContribution(0);
        identInspector.setInsNegative(0);
        LoginInspector loginInspector2=adminInspectorService.getLoginInspector(loginInspector.getInAccount());
        identInspector.setInsCid(loginInspector2.getInId());
        adminInspectorService.setIdentInspector(identInspector);
        model.addAttribute("success",1);
        return "forward:/admin/manage-AQI/page/1";
    }
}
