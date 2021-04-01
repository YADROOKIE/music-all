package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.SongListMusic;
import com.yad.web.entity.UserSongList;
import com.yad.web.mapper.UserSongListMapper;
import com.yad.web.service.SongListMusicService;
import com.yad.web.service.UserSongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yad
 * @since 2021-03-29
 */
@Service
public class UserSongListServiceImpl extends ServiceImpl<UserSongListMapper, UserSongList> implements UserSongListService {
    @Autowired
    private SongListMusicService songListMusicService;

    @Override
    public boolean removeSongList(Integer id) {
        QueryWrapper<SongListMusic> wrapper = new QueryWrapper<>();
        wrapper.eq("l_id",id);
        songListMusicService.remove(wrapper);


        return this.removeById(id);
    }
}
