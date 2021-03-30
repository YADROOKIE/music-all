package com.yad.web.controller.commodity;


import com.yad.web.entity.BaseUser;
import com.yad.web.entity.CommodityShare;
import com.yad.web.entity.vo.CommodityVo;
import com.yad.web.entity.vo.ReplyVo;
import com.yad.web.service.CommodityReplyService;
import com.yad.web.service.CommodityShareService;
import com.yad.web.utils.Hepler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/share/commodity/view")
public class ViewCommodityController {
    @Autowired
    private CommodityShareService commodityShareService;
    @Autowired
    private CommodityReplyService replyService;

    @GetMapping("/add")
    public  String addCommodityView(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user==null){
            return  "login.html";
        }
        return  "commodity/add.html";
    }

    @GetMapping("/{id}")
    public  String commodityInfo(@PathVariable String id, Model model,HttpServletRequest request){
        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            model.addAttribute("user",user);
        }
        CommodityShare cs = commodityShareService.getById(id);
        if (cs==null){
            return  "commodity/info404.html";
        }
        model.addAttribute("cs",cs);
        List<ReplyVo> replyVos = replyService.listOfCommodity(id);
        model.addAttribute("list",replyVos);

        return  "commodity/info.html";
    }


    @GetMapping("/user/publish/{id}")
    public  String userPublishedCom(@PathVariable String id,HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        BaseUser user = (BaseUser) session.getAttribute("user");
        if (user!=null){
            model.addAttribute("user",user);
        }
        List<CommodityVo> list = commodityShareService.getUserPublishComs(id);
        model.addAttribute("list",list);
        return  "user/publish.html";
    }

    @GetMapping("/edit/{id}")
    public  String   edit(@PathVariable String id,HttpServletRequest request,Model model){
        BaseUser user = Hepler.getUser(request);
        CommodityVo cds = commodityShareService.getComVoById(id);
        if (!cds.getUserId().equals(user.getId())){
            model.addAttribute("msg","非法用户操作");
            return  "tip/error.html";
        }
        model.addAttribute("cds",cds);
        return  "commodity/edit.html";
    }
}
