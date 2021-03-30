package com.yad.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.CommodityShare;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.utils.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
public interface CommodityShareService extends IService<CommodityShare> {
    R addCommodity(CommodityVo commodityVo);

    List<CommodityVo> loadCommodity(QueryWrapper<CommodityShare> w);

    CommodityVo getComVoById(String commodityId);

    List<CommodityVo> getUserPublishComs(String id);

    R updateCommodity(CommodityVo commodityVo);
}
