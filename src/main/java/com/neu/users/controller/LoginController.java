package com.neu.users.controller;

import com.neu.users.entity.IdentUser;
import com.neu.users.entity.LoginUser;
import com.neu.users.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/signinJudgment")
    public String signinJudgment(String account, String pass1,String pass2, Model model){
        if(account.equals("")||pass1.equals("")||pass2.equals("")){
            model.addAttribute("account",account);
            model.addAttribute("confirm",4);
            return "users/login/signin";
        }
        if(!pass1.equals(pass2)){
            model.addAttribute("account",account);
            model.addAttribute("confirm",2);
            return "users/login/signin";
        }
        List<LoginUser> list=loginService.selectAccount(account);
        if(list.size()!=0){
            model.addAttribute("confirm",3);
            return "users/login/signin";
        }

        int a=loginService.signIn(account,pass1);
        if(a>0){
            model.addAttribute("judgment",1);
            return "forward:/user/login";
        }else{
            model.addAttribute("judgment",0);
            return "users/login/signin";
        }
    }

    @RequestMapping("/signin")
    public String signin(Model model){
        model.addAttribute("confirm",1);
        return "users/login/signin";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("confirm",0);
        return "users/login/login";
    }

    @RequestMapping("/loginJudgment")
    public String loginJudgment(String account, String pass, Model model, HttpSession httpSession){
        List<LoginUser> list=loginService.selectAccount(account);
        if(list.size()==0){
            model.addAttribute("account",account);
            model.addAttribute("confirm",1);
            return "users/login/login";
        }
        if(!list.get(0).getUsPass().equals(pass)){
            model.addAttribute("account",account);
            model.addAttribute("confirm",1);
            return "users/login/login";
        }
        if(list.get(0).getUsOut()==1){
            model.addAttribute("account",account);
            model.addAttribute("banned",1);
            return "users/login/login";
        }
        httpSession.setAttribute("user",list.get(0));
        return "redirect:/user";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/user";
    }

    @RequestMapping("/selectIdentbyid")
    public String selectIdentByid(Model model,HttpSession httpSession){
        LoginUser loginUser= (LoginUser) httpSession.getAttribute("user");
        IdentUser identUser=loginService.selectIdentByid(loginUser.getUsId());
        model.addAttribute("personal",identUser);
        return "users/user/personal";
    }

    @RequestMapping("/updateIdent")
    public String updateIdent(Model model, IdentUser identUser, HttpSession httpSession, MultipartFile file1) throws IOException {
        if(file1.getSize()!=0) {
            //获取上传的文件的文件名
            String fileName = file1.getOriginalFilename();
            //处理文件重名问题
            String hzName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + hzName;
            //获取服务器中file目录的路径
            ClassLoader classLoader = LoginController.class.getClassLoader();
            String targetPath = classLoader.getResource("").getPath();
            String photoPath = targetPath+ "static/users/image";
            File file = new File(photoPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String finalPath = photoPath + "/" + fileName;
            //实现上传功能
            file1.transferTo(new File(finalPath));
            identUser.setUsePhoto("/users/image/" + fileName);
        }
        LoginUser loginUser= (LoginUser) httpSession.getAttribute("user");
       identUser.setUseCid(loginUser.getUsId());
        int a=loginService.updateIdent(identUser);
        return "redirect:/user/selectIdentbyid";
    }
}
