package com.yad.web.service;

import com.yad.web.entity.BaseMusic;
import com.yad.web.entity.SongListMusic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yad.web.utils.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yad
 * @since 2021-03-29
 */
public interface SongListMusicService extends IService<SongListMusic> {

    R addMusicToList(SongListMusic music);

    List<BaseMusic> getListAllMusicByListId(Integer id);
}
