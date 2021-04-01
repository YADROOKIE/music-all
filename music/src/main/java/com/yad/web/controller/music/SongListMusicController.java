package com.yad.web.controller.music;


import com.yad.web.entity.BaseMusic;
import com.yad.web.entity.SongListMusic;
import com.yad.web.service.SongListMusicService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2021-03-29
 */
@CrossOrigin
@RestController
@RequestMapping("/web/song-list-music")
public class SongListMusicController {
    @Autowired
    private SongListMusicService songListMusicService;

    //添加到歌单
    @PostMapping("/addtolist")
    public  R addToList(@RequestBody SongListMusic music    ){

        return songListMusicService.addMusicToList(music);
    }

    @PostMapping("/delete/{id}")
    public  R deleteById(@PathVariable Integer id){
        if (id==null){
            return  R.error();
        }
        boolean b = songListMusicService.removeById(id);
        return  b ? R.ok() : R.error();
    }

    @GetMapping("/list/{id}")
    public  R getListAllMusicByListId(@PathVariable Integer id  ){
        List<BaseMusic> list = songListMusicService.getListAllMusicByListId(id);
        return  R.ok().data("list",list);
    }
}

