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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CommodityShare getCommodity() {
		return commodity;
	}

	public void setCommodity(CommodityShare commodity) {
		this.commodity = commodity;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
    
}
