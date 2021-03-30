package com.yad.web.controller;

import com.yad.web.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @PostMapping("/error")
    @ResponseBody
    public R error(HttpServletRequest request){
        String error = (String) request.getAttribute("error");
        return  R.error().message(error);
    }

}
