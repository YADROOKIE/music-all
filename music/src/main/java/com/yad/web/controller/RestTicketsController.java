package com.yad.web.controller;

import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.entity.vo.UcOrderVO;
import com.yad.web.param.BuyTicketParam;
import com.yad.web.service.CommodityShareService;
import com.yad.web.service.UcOrderService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/share/ticket")
public class RestTicketsController {
    @Autowired
    private CommodityShareService commodityShareService;
    @Autowired
    private UcOrderService orderService ;

    @GetMapping("/list")
    public R listTicket(){

        List<CommodityShare> list = commodityShareService.list(null);
        return  R.ok().data("list",list);
    }

    @PostMapping("/buy")
    public  R butTicket(@RequestBody BuyTicketParam param){
        boolean ok = commodityShareService.buyTicket(param);
        return  ok ? R.ok() :R.error();
    }

    @GetMapping("/myorder/{id}")
    public  R getMyOrder(@PathVariable("id") String id){
        List<UcOrderVO> userOrderList = orderService.getUserOrderList(id);
        return  R.ok().data("list",userOrderList);
    }
}
