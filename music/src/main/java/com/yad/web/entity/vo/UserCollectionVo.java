package com.yad.web.entity.vo;

import lombok.Data;

import java.util.Date;


@Data
public class UserCollectionVo {
    private Integer id;

    private String userId;

    private  Integer count;
    private CommodityVo commodity;

    private Date createAt;
}
