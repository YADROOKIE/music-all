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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public CommodityVo getCommodity() {
		return commodity;
	}

	public void setCommodity(CommodityVo commodity) {
		this.commodity = commodity;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
    
}
