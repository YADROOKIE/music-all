package com.yad.web.service;

import com.yad.web.entity.BaseUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yad.web.entity.vo.UserLoginVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
public interface BaseUserService extends IService<BaseUser> {
    BaseUser login(UserLoginVo user);

    boolean register(BaseUser user);
}
