package com.yad.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.BaseMusic;
import com.yad.web.entity.SongListMusic;
import com.yad.web.mapper.SongListMusicMapper;
import com.yad.web.service.BaseMusicService;
import com.yad.web.service.SongListMusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yad.web.utils.R;
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
 * @since 2021-03-29
 */
@Service
public class SongListMusicServiceImpl extends ServiceImpl<SongListMusicMapper, SongListMusic> implements SongListMusicService {

    @Override
    public R addMusicToList(SongListMusic music) {
        QueryWrapper<SongListMusic> wrapper = new QueryWrapper<>();
        wrapper.eq("list_id", music.getListId());
        List<SongListMusic> list = this.list(wrapper);
        for (SongListMusic songListMusic : list) {
            if (songListMusic.getMusicId().equals(music.getMusicId())){
                return  R.error().message("该歌单已添加该歌曲");
            }
        }
        boolean save = this.save(music);
        return save ? R.ok() : R.error().message("添加失败");
    }
    @Autowired
    private BaseMusicService musicService;

    @Override
    public List<BaseMusic> getListAllMusicByListId(Integer id) {
        QueryWrapper<SongListMusic> wrapper = new QueryWrapper<>();
        wrapper.eq("list_id", id);
        List<SongListMusic> list = this.list(wrapper);

        List<BaseMusic> result = new ArrayList<>();
        for (SongListMusic songListMusic : list) {
            BaseMusic music = musicService.getById(songListMusic.getId());
            result.add(music);
        }
        return result;
    }
}
