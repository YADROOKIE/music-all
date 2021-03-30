package com.yad.web.controller.commodity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.BaseUser;
import com.yad.web.entity.UserComCollection;
import com.yad.web.entity.vo.UserCollectionVo;
import com.yad.web.service.UserComCollectionService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2020-12-24
 */
@Controller
@RequestMapping("/share/user-com-collection")
public class UserComCollectionController {
    @Autowired
    private UserComCollectionService collectionService ;
    //view
    @GetMapping("/user/collection")
    public  String userCollections( Model model ,HttpServletRequest request){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");

        model.addAttribute("user",user);
        List<UserCollectionVo> list = collectionService.getUserCollections(user.getId());
        model.addAttribute("list",list);
        return "user/collections.html";
    }

    //rest
    @PostMapping("/add/{id}/{count}")
    @ResponseBody
    public R getCollection(@PathVariable("id") String id,
                           @PathVariable("count") Integer count,
                           HttpServletRequest request){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");


        UserComCollection collection = new UserComCollection();
        collection.setCommodityId(id);
        collection.setUserId(user.getId());
        collection.setCount(count);
        collectionService.save(collection);

        return R.ok();
    }

    @PostMapping("/cancel/{id}")
    @ResponseBody
    public  R cancelCollect(@PathVariable("id") String id ,HttpServletRequest request){
        collectionService.removeById(Integer.valueOf(id));
        return  R.ok();
    }
}

