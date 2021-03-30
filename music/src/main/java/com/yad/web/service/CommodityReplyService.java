package com.yad.web.service;

import com.yad.web.entity.CommodityReply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yad.web.entity.vo.ReplyVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
public interface CommodityReplyService extends IService<CommodityReply> {

    List<ReplyVo> listOfCommodity(String id);
}
