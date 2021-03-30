package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.UserComCollection;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.entity.vo.UserCollectionVo;
import com.yad.web.mapper.UserComCollectionMapper;
import com.yad.web.service.CommodityShareService;
import com.yad.web.service.UserComCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserComCollectionServiceImpl extends ServiceImpl<UserComCollectionMapper, UserComCollection> implements UserComCollectionService {
    @Autowired
    private CommodityShareService commodityShareService;

    @Override
    public List<UserCollectionVo> getUserCollections(String id) {
        QueryWrapper<UserComCollection> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);

        List<UserComCollection> list = this.list(wrapper);
        List<UserCollectionVo> result = new ArrayList<>();
        for (UserComCollection collection : list) {
            CommodityVo vo = commodityShareService.getComVoById(collection.getCommodityId()  );
            UserCollectionVo v = new UserCollectionVo();
            v.setCommodity(vo);
            v.setCount(collection.getCount());
            v.setId(collection.getId());
            result.add(v);
        }
        return  result;
    }
}
