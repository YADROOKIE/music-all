package com.yad.web.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CommodityVo {
    private String id;

    private String userId;

    private String name;

    private String type;

    private Integer count;

    private BigDecimal price;

    private Integer viewCount;


    private String comment;

    private Date createAt;

    private Date updateAt;
    private List<String> pictures ;
}
