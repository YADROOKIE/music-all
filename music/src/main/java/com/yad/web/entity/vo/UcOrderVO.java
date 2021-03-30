package com.yad.web.entity.vo;

import com.yad.web.entity.CommodityShare;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UcOrderVO {
    private String id;

    private CommodityShare commodity;


    private String userId;


    private Integer count;


    private BigDecimal price;

    private Date createAt;

    private Date updateAt;
}
