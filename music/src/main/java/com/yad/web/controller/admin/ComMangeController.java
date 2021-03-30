package com.yad.web.controller.admin;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.service.BaseUserService;
import com.yad.web.service.CommodityShareService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/manage/commodity")
public class ComMangeController {
    @Autowired
    private CommodityShareService commodityShareService;

    @GetMapping("")
    public  String index(Model model){
        List<CommodityVo> list = commodityShareService.loadCommodity(null);
        model.addAttribute("list",list);
        return  "admin/comManage.html";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public R   deleteUser(@PathVariable("id")String id){
        boolean b = commodityShareService.removeById(id);
        return  b? R.ok() : R.error();
    }
}
