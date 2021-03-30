package com.yad.web.controller.admin;


import com.yad.web.entity.BaseUser;
import com.yad.web.service.BaseUserService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/manage/user")
public class UserMangeController {
    @Autowired
    private BaseUserService userService;

    @GetMapping("")
    public  String index(Model model){
        List<BaseUser> list = userService.list(null);
        model.addAttribute("list",list);
        return  "admin/userManage.html";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public R   deleteUser(@PathVariable("id")String id){
        boolean b = userService.removeById(id);
        return  b? R.ok() : R.error();
    }
}
