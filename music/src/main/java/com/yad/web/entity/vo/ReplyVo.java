package com.yad.web.entity.vo;

import com.yad.web.entity.BaseUser;
import lombok.Data;

import java.util.Date;

@Data
public class ReplyVo {
    private String id;

    private String commodityId;

    private BaseUser user;

    private String content;

    private Date createAt;

    private Date updateAt;


}
