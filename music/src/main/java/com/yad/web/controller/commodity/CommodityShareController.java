package com.yad.web.controller.commodity;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.service.CommodityShareService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/share/commodity/rest")
public class CommodityShareController {
    @Autowired
    private CommodityShareService commodityShareService;

    @PostMapping("/add")
    public R addCommodity(@RequestBody CommodityVo commodityVo, HttpServletRequest request){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        if (user==null){
            return  R.error().message("未登录");
        }
        System.out.println(commodityVo);
        commodityVo.setUserId(user.getId());
        R r  = commodityShareService.addCommodity(commodityVo);
        return  r;
    }

    @PostMapping("/update")
    public R updateCommodity(@RequestBody CommodityVo commodityVo, HttpServletRequest request){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        if (user==null){
            return  R.error().message("未登录");
        }
        System.out.println(commodityVo);
        commodityVo.setUserId(user.getId());
        R r  = commodityShareService.updateCommodity(commodityVo);
        return  r;
    }
}

