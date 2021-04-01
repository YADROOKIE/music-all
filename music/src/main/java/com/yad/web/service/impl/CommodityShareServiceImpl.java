package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.CommodityPicture;
import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.UcOrder;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.mapper.CommodityShareMapper;
import com.yad.web.param.BuyTicketParam;
import com.yad.web.service.CommodityPictureService;
import com.yad.web.service.CommodityShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yad.web.service.UcOrderService;
import com.yad.web.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
@Service
public class CommodityShareServiceImpl extends ServiceImpl<CommodityShareMapper, CommodityShare> implements CommodityShareService {
    @Autowired
    private CommodityPictureService pictureService;


    @Override
    @Transactional
    public R addCommodity(CommodityVo commodityVo) {
        try {
            CommodityShare c = new CommodityShare();
            BeanUtils.copyProperties(commodityVo,c);
            this.save(c);

            if (commodityVo.getPictures()!=null){
                commodityVo.getPictures().forEach(p->{
                    pictureService.save(new CommodityPicture(c.getId(),p));
                });
            }

            return  R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Override
    public List<CommodityVo> loadCommodity(QueryWrapper<CommodityShare> w) {
        List<CommodityShare> list = this.list(w);

        List<CommodityVo> voList = new ArrayList<>();;

        for (CommodityShare commodityShare : list) {
            CommodityVo v = new CommodityVo();
            BeanUtils.copyProperties(commodityShare,v);

            QueryWrapper<CommodityPicture> wrapper = new QueryWrapper<>();
            wrapper.eq("commodity_id",commodityShare.getId());
            CommodityPicture one = pictureService.getOne(wrapper);
            v.setPictures(Arrays.asList(one.getUrl()));
            voList.add(v);
        }
        return  voList;
    }

    @Override
    public CommodityVo getComVoById(String commodityId) {
        CommodityShare byId = this.getById(commodityId);
        if (byId==null){
            return  null;
        }
        QueryWrapper<CommodityPicture> wrapper = new QueryWrapper<>();
        wrapper.eq("commodity_id",byId.getId());
        List<CommodityPicture> pictures = pictureService.list(wrapper);
        List<String> picturesList = new ArrayList<>();
        pictures.forEach(c->{picturesList.add(c.getUrl());});
        CommodityVo v = new CommodityVo();
        BeanUtils.copyProperties(byId,v);
        v.setPictures(picturesList);
        return  v;
    }

    @Override
    public List<CommodityVo> getUserPublishComs(String id) {
        QueryWrapper<CommodityShare> w = new QueryWrapper<>();
        w.eq("user_id",id);
        return  this.loadCommodity(w);
    }

    @Override
    @Transactional
    public R updateCommodity(CommodityVo commodityVo) {
        CommodityShare commodityShare = new CommodityShare();
        BeanUtils.copyProperties(commodityVo,commodityShare);
        this.updateById(commodityShare);

        QueryWrapper<CommodityPicture> wrapper = new QueryWrapper<>();
        wrapper.eq("commodity_id",commodityShare.getId());
        pictureService.remove(wrapper   );

        for (String picture : commodityVo.getPictures()) {
            pictureService.save(new CommodityPicture(commodityVo.getId(),picture));
        }
        return  R.ok();
    }


    @Autowired
    private UcOrderService orderService;
    @Override
    public boolean buyTicket(BuyTicketParam param) {
        UcOrder order = new UcOrder();
        order.setCommodityId(param.getTicketId());
        order.setUserId(param.getUserId());
        order.setCount(1);
        order.setPrice(BigDecimal.ONE);
        return orderService.save(order);
    }
}
