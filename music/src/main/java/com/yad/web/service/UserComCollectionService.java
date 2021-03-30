package com.yad.web.service;

import com.yad.web.entity.UserComCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yad.web.entity.vo.UserCollectionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
public interface UserComCollectionService extends IService<UserComCollection> {
    List<UserCollectionVo> getUserCollections(String id);
}
