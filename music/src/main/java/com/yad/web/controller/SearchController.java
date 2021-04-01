package com.yad.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.BaseMusic;
import com.yad.web.entity.CommodityShare;
import com.yad.web.service.BaseMusicService;
import com.yad.web.service.CommodityShareService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private BaseMusicService musicService;

    @GetMapping("/global/music")
    public R globalSearchMusic(@RequestParam("query")String query){
        QueryWrapper<BaseMusic> wrapper = new QueryWrapper<>();
        wrapper.like("music_name",query);
        wrapper.or().like("singer",query);

        List<BaseMusic> list = musicService.list(wrapper);
        return  R.ok().data("list",list);
    }

    @Autowired
    private CommodityShareService commodityShareService;

    @GetMapping("/ticket")
    public  R ticketSearch(@RequestParam("query") String query){
        QueryWrapper<CommodityShare> wrapper = new QueryWrapper<>();
        wrapper.like("name",query);
        List<CommodityShare> list = commodityShareService.list(wrapper);
        return  R.ok().data("list",list);
    }

}
