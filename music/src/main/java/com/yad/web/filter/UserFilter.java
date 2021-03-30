package com.yad.web.filter;

import com.yad.web.entity.BaseUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@WebFilter(filterName = "userfilter",urlPatterns = {"/share/user-com-collection/*","/share/commodity/uc-order/*",
                                                    "/share/commodity/view/edit/*","/share/commodity/view/user/publish/*"})
public class UserFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");

        if (user==null){
            if (request.getMethod().equals("GET")){
                request.getRequestDispatcher("/share/base-user/login.html").forward(servletRequest, servletResponse);
            }else if (request.getMethod().equals("POST")){
                request.setAttribute("error","未登录请求出错请重新登录");
                request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
            }
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
