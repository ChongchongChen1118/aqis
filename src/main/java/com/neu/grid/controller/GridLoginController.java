package com.neu.grid.controller;

import com.neu.grid.dto.IdentInspectorDTO;
import com.neu.grid.entity.LoginInspector;
import com.neu.grid.service.GridLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/grid")
public class GridLoginController {
    @Autowired
    GridLoginService gridLoginService;

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("confirm",0);
        return "grid/login/login";
    }

    @RequestMapping("/loginJudgment")
    public String loginJudgment(String account, String pass, Model model, HttpSession httpSession){
        LoginInspector loginInspector= gridLoginService.loginInspector(account,pass);
        if(loginInspector==null||!loginInspector.getInPass().equals(pass)){
            model.addAttribute("confirm",1);
            return "grid/login/login";
        }
        httpSession.setAttribute("grid",loginInspector);
        return "redirect:/grid/tobesolved/1";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("grid");
        return "redirect:/grid/login";
    }

    @RequestMapping("/personal")
    public String personal(Model model,HttpSession httpSession){
        LoginInspector loginInspector=(LoginInspector) httpSession.getAttribute("grid");
        if(loginInspector==null){
            return "redirect:/grid/login";
        }
        IdentInspectorDTO identInspectorDTO=gridLoginService.personalById(loginInspector.getInId());
        String a=identInspectorDTO.getInsCard();
        String b=a.substring(0,4);
        String c=a.substring(14,18);
        model.addAttribute("personal",identInspectorDTO);
        model.addAttribute("b",b);
        model.addAttribute("c",c);
        return "grid/login/personal";
    }

    @RequestMapping("/about")
    public String about(){
        return "grid/feature/propaganda";
    }
}
