package com.yad.web.service;

import com.yad.web.entity.BaseMusic;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yad
 * @since 2021-03-29
 */
public interface BaseMusicService extends IService<BaseMusic> {

    void indexMusic(Model model);
}
