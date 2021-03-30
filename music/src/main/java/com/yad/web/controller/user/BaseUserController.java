package com.yad.web.controller.user;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.vo.UserLoginVo;
import com.yad.web.service.BaseUserService;
import com.yad.web.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2020-12-21
 */

@Controller
@RequestMapping("/share/base-user")
public class BaseUserController {
    @Autowired
    private BaseUserService userService;

    @PostMapping("/register")
    public  String  viewRegister(@RequestParam("stuNo")String stuNo,
                                 @RequestParam("name")String name ,
                                 @RequestParam("age")Integer age,
                                 @RequestParam("password")String password){
        BaseUser user = new BaseUser(name,stuNo,age,MD5.encrypt(password));
        boolean save = userService.register(user);
        return  save?   "tip/success.html" : "tip/fail.html";
    }

    @PostMapping("/login")
    public  String viewLogin(@RequestParam("stuNo")String stuNo,
                             @RequestParam("password")String password, HttpServletRequest request,
                             Model model){
        BaseUser baseUser = userService.login(new UserLoginVo(stuNo,password));
        if (baseUser==null){
            return "redirect:/share/base-user/login.html";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",baseUser);
        model.addAttribute("user",baseUser);
        return  "redirect:/";
    }
    @GetMapping("/logout")
    public  String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return  "redirect:/";
    }

    @GetMapping("/login.html")
    public  String login(){
        return  "login.html";
    }

    @GetMapping("/register")
    public  String register(){
        return  "register.html";
    }


    @GetMapping("/person")
    public  String personInfo(HttpServletRequest request,Model model){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        if (user==null){
            return  "index.html";
        }
        model.addAttribute("user",user);
        return  "person.html";
    }
}

