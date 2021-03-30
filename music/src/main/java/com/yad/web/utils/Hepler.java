package com.yad.web.utils;

import com.yad.web.entity.BaseUser;

import javax.servlet.http.HttpServletRequest;

public class Hepler {
    public  static BaseUser getUser(HttpServletRequest request){
        return (BaseUser) request.getSession().getAttribute("user");
    }
}
