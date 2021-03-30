package com.yad.web.controller.music;


import com.yad.web.entity.BaseMusic;
import com.yad.web.service.BaseMusicService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/web/base-music")
public class BaseMusicController {
    @Autowired
    private BaseMusicService musicService;

    @PostMapping("/add")
    public R  addMusic(@RequestBody BaseMusic music){
        boolean save = musicService.save(music);
        return   save ? R.ok() : R.error().message("添加失败");
    }

    @GetMapping("/{id}")
    public  R  getMusic(@PathVariable("id") Integer id      ){
        BaseMusic music = musicService.getById(id);
        return  R.ok().data("music",music);
    }

    @GetMapping("/hots")
    public  R getHots(){
        List<BaseMusic> list = musicService.list(null);
        return  R.ok().data("list",list);
    }
}

