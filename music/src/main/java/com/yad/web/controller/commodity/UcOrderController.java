package com.yad.web.controller.commodity;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.UcOrder;
import com.yad.web.entity.vo.UcOrderVO;
import com.yad.web.service.UcOrderService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2020-12-24
 */
@Controller
@RequestMapping("/share/commodity/uc-order")
public class UcOrderController {

    @Autowired
    private UcOrderService orderService;

    @PostMapping("/closeAccount")
    @ResponseBody
    public R  closeAccount(HttpServletRequest request){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        R r = orderService.closeAccount(user.getId());
        return  r;
    }

    @GetMapping("/list")
    public String orderList(HttpServletRequest request , Model model){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        List<UcOrderVO> orderVOList = orderService.getUserOrderList(user.getId());
        model.addAttribute("list",orderVOList);
        model.addAttribute("user",user);
        return  "user/orderList.html";
    }
}

