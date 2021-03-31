package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.UcOrder;
import com.yad.web.entity.UserComCollection;
import com.yad.web.entity.vo.UcOrderVO;
import com.yad.web.exception.MyException;
import com.yad.web.mapper.UcOrderMapper;
import com.yad.web.service.CommodityShareService;
import com.yad.web.service.UcOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yad.web.service.UserComCollectionService;
import com.yad.web.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
public class UcOrderServiceImpl extends ServiceImpl<UcOrderMapper, UcOrder> implements UcOrderService {
    @Autowired
    private UserComCollectionService collectionService;
    @Autowired
    private CommodityShareService commodityService;

    @Override
    @Transactional
    public R closeAccount(String id) {
//        collectionService.lockWrite();   加写锁
        //1 找出购物车所有物品
        QueryWrapper<UserComCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<UserComCollection> collections = collectionService.list(wrapper);

        BigDecimal price = BigDecimal.ZERO;
        for (UserComCollection collection : collections) {
            CommodityShare cds = commodityService.getById(collection.getCommodityId());
            if (cds==null){
                throw  new MyException(20001,"有商品已下架无法结算");
            }
            //检查库存是否足够
            if (cds.getCount()<collection.getCount()){
                throw new MyException(20001,cds.getName()+"---库存不足  请重新加入购物车");
            }
            //在订单表中插入
            UcOrder order = new UcOrder();
            order.setCommodityId(cds.getId());
            order.setCount(collection.getCount());
            BigDecimal p = cds.getPrice().multiply(BigDecimal.valueOf(collection.getCount().longValue()));
            order.setPrice(p);
            order.setUserId(id);
            this.save(order);
            //更新库存
            cds.setCount(cds.getCount()-collection.getCount());
            commodityService.updateById(cds);
            //删除购物车里的物品
            collectionService.removeById(collection.getId());
            //计算总价
            price.add(p);
        }
//        collectionService.unLock();  释放锁

        return  R.ok().message("结算完成").data("price",price);
    }

    @Override
    public List<UcOrderVO> getUserOrderList(String id) {
        QueryWrapper<UcOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<UcOrder> list = this.list(wrapper);
        List<UcOrderVO> vos = new ArrayList<>();
        for (UcOrder order : list) {
            CommodityShare cds = commodityService.getById(order.getCommodityId());
            UcOrderVO v = new UcOrderVO();
            BeanUtils.copyProperties(order,v);
            v.setCommodity(cds);
            vos.add(v);
        }
        return  vos;
    }
}
