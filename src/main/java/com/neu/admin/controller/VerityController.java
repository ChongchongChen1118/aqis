package com.neu.admin.controller;

import com.neu.admin.entity.VerifyAdmin;
import com.neu.admin.service.AdminBoardDataService;
import com.neu.admin.service.AdminVerityAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import java.util.List;


@Controller
@RequestMapping("/verity")
public class VerityController {
    @Autowired
    private AdminVerityAdminService adminVerityAdminService;
    @Autowired
    private AdminBoardDataService adminBoardDataService;

    @RequestMapping("/handler/admin-login")
    public String login(VerifyAdmin verifyAdmin, HttpSession session, Model model){
        List<VerifyAdmin> verifyAdminList= adminVerityAdminService.getAdminList(verifyAdmin);
        if(!CollectionUtils.isEmpty(verifyAdminList)){
            session.setAttribute("USER_INFO",verifyAdminList.get(0));//用户信息
            return "forward:/admin/admin-index";
        }
        model.addAttribute("msg", "账号或密码错误，请重新输入！");
        return "forward:/admin";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("USER_INFO");
        return "forward:/admin";
    }

}
