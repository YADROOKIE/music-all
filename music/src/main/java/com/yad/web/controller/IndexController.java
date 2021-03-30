package com.yad.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.BaseUser;
import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.service.CommodityShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CommodityShareService  commodityShareService;

    @GetMapping("/")
    public  String index(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        BaseUser user = (BaseUser) session.getAttribute("user");
        if (user!=null){
            model.addAttribute("user",user);
        }
        List<CommodityVo> list = commodityShareService.loadCommodity(null);
        model.addAttribute("list",list);
        return  "index.html";
    }

    @GetMapping("/search/{key}")
    public  String search(HttpServletRequest request, Model model,@PathVariable("key")  String key){
        HttpSession session = request.getSession();
        BaseUser user = (BaseUser) session.getAttribute("user");
        if (user!=null){
            model.addAttribute("user",user);
        }
        QueryWrapper<CommodityShare> wrapper = new QueryWrapper<>();
        wrapper.like("name",key);
        List<CommodityVo> list = commodityShareService.loadCommodity(wrapper);
        model.addAttribute("list",list);
        return  "index.html";
    }
}
