package com.yad.web.controller.music;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.UserSongList;
import com.yad.web.service.UserSongListService;
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
 * 前端控制器
 * </p>
 *
 * @author yad
 * @since 2021-03-29
 */
@CrossOrigin
@RestController
@RequestMapping("/web/user-song-list")
public class UserSongListController {
    @Autowired
    private UserSongListService listService;

    @PostMapping("add")
    public R addSongCollect(@RequestBody UserSongList songList) {
        boolean save = listService.save(songList);
        return  save ? R.ok() :R.error();
    }

    @PostMapping("/delete/{id}")
    public  R deleteSongCollect(@PathVariable Integer id   ){
        boolean b = listService.removeSongList(id);

        return  b ? R.ok() :R.error();
    }

    @GetMapping("/list/{id}")
    public  R getUserSongList(@PathVariable String id ){
        QueryWrapper<UserSongList> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<UserSongList> list = listService.list(wrapper);
        return  R.ok().data("list",list);
    }
}

