package com.yad.web.service.impl;

import com.yad.web.entity.BaseMusic;
import com.yad.web.mapper.BaseMusicMapper;
import com.yad.web.service.BaseMusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yad
 * @since 2021-03-29
 */
@Service
public class BaseMusicServiceImpl extends ServiceImpl<BaseMusicMapper, BaseMusic> implements BaseMusicService {

    @Override
    public void indexMusic(Model model) {
        List<BaseMusic> hotlist = this.list(null);

        model.addAttribute("hots",hotlist);
    }
}
