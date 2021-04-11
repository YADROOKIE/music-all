package com.yad.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yad
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommodityPicture implements Serializable {

    private static final long serialVersionUID = 1L;
    public   CommodityPicture(String commodityId,String url){
        this.url = url;
        this.commodityId = commodityId;
    }

    public CommodityPicture(Integer id, String commodityId, String url) {
        this.id = id;
        this.commodityId = commodityId;
        this.url = url;
    }
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物品id
     */
    private String commodityId;

    /**
     * 图片地址
     */
    private String url;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
