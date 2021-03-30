package com.yad.web.controller.admin;

import com.yad.web.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BaseUserService userService ;

    @GetMapping("")
    public  String adminIndex(){
        return  "admin/login.html";
    }

    @PostMapping("/login")
    public  String adminLogin(@RequestParam("username")String username,
                              @RequestParam("password")String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("admin","admin");
        return  "admin/adminIndex.html";
    }

}
