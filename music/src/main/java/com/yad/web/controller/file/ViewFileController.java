package com.yad.web.controller.file;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.UserFileVo;
import com.yad.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value ="/file")
public class ViewFileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public  String fileIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        BaseUser user = (BaseUser) session.getAttribute("user");
        if (user==null){
            return  "login.html";
        }
        model.addAttribute("user",user);
        UserFileVo vo = fileService.loadUserFiles(user.getId());
        System.out.println(vo);
        model.addAttribute("root",vo);
        return  "file/index.html";
    }

    @GetMapping("/{id}")
    public  String fileIndex(HttpServletRequest request, Model model, @PathVariable String id){
        HttpSession session = request.getSession();
        BaseUser user = (BaseUser) session.getAttribute("user");
        if (user==null){
            return  "login.html";
        }
        model.addAttribute("user",user);
        UserFileVo vo = fileService.loadUserFiles(user.getId(),id);
        System.out.println(vo);
        model.addAttribute("root",vo);
        return  "file/index.html";
    }
}
