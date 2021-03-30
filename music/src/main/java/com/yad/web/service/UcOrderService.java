package com.yad.web.service;

import com.yad.web.entity.UcOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yad.web.entity.vo.UcOrderVO;
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
public interface UcOrderService extends IService<UcOrder> {

    R closeAccount(String id);

    List<UcOrderVO> getUserOrderList(String id);
}
