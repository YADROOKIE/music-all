package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.BaseUser;
import com.yad.web.entity.CommodityReply;
import com.yad.web.entity.vo.ReplyVo;
import com.yad.web.mapper.CommodityReplyMapper;
import com.yad.web.service.BaseUserService;
import com.yad.web.service.CommodityReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
public class CommodityReplyServiceImpl extends ServiceImpl<CommodityReplyMapper, CommodityReply> implements CommodityReplyService {
    @Autowired
    private BaseUserService userService;

    @Override
    public List<ReplyVo> listOfCommodity(String id) {
        List<ReplyVo> replyVos = new ArrayList<>();

        QueryWrapper<CommodityReply> wrapper = new QueryWrapper<>();
        wrapper.eq("commodity_id",id);
        List<CommodityReply> list = this.list(wrapper);
        list.forEach(r->{
            ReplyVo v = new ReplyVo() ;
            BeanUtils.copyProperties(r,v);
            BaseUser usr = userService.getById(r.getUserId());
            v.setUser(usr);
            replyVos.add(v);
        });
        return  replyVos;
    }
}
