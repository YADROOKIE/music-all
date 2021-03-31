package com.yad.web.controller.user;

import com.yad.web.entity.BaseUser;
import com.yad.web.service.BaseUserService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/share/base-user/rest")
public class RestBaseUserController {
    @Autowired
    private BaseUserService userService;

    @PostMapping("/update")
    public R updateUser(@RequestBody BaseUser user, HttpServletRequest request){

        boolean b = userService.updateById(user);
        request.getSession().setAttribute("user",user);

        return  b ? R.ok():R.error();
    }

    @PostMapping("/login")
    public  R userRestLogin(@RequestBody BaseUser baseUser){
        BaseUser user = userService.restLogin(baseUser);
        return  user != null ? R.ok().data("user",user) : R.error();
    }

    @PostMapping("/register")
    public  R userRegiser(@RequestBody BaseUser baseUser){
        boolean save = userService.save(baseUser);
        return  save ? R.ok() : R.error();
    }
}
