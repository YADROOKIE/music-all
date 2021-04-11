package com.yad.web.controller.commodity;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.service.CommodityShareService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2020-12-24
 */
@CrossOrigin
@RestController
@RequestMapping("/share/commodity/rest")
public class CommodityShareController {
    @Autowired
    private CommodityShareService commodityShareService;

    @PostMapping("/add")
    public R addCommodity(@RequestBody CommodityShare commodityShare){
        boolean save = commodityShareService.save(commodityShare);

        return  save? R.ok() :R.error();
    }

}

