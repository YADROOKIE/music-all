package com.yad.web.controller.music;

import com.yad.web.service.BaseMusicService;
import com.yad.web.util.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private BaseMusicService musicService;

    @GetMapping
    public  String  index(HttpServletRequest request, Model model){
        ControllerUtils.setUserSession(request,model);

        musicService.indexMusic(model);
        return  "music/index.html";
    }
}

