package com.yad.web.controller.user;

import com.yad.web.entity.vo.AdminUser;
import com.yad.web.utils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/web/user")
public class WebUserController {
    @PostMapping("/login")
    public R login(@RequestBody AdminUser adminUser){
        System.out.println(adminUser);
        return  R.ok().data("token","ok");
    }
}
