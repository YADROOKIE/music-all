package com.yad.web.controller.commodity;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.CommodityReply;
import com.yad.web.service.CommodityReplyService;
import com.yad.web.utils.Hepler;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yad
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/share/commodity-reply")
public class CommodityReplyController {
    @Autowired
    private CommodityReplyService replyService;

    @PostMapping("add")
    public R   addReply(@RequestBody CommodityReply reply,
                        HttpServletRequest request){
        BaseUser user = Hepler.getUser(request);
        if (user==null){
            return  R.error();
        }
        reply.setUserId(user.getId());
        boolean save = replyService.save(reply);
        return  save ? R.ok() : R.error();
    }
}

