package com.yad.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yad
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("file_meta")
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件hash值
     */
    @TableId(value = "id", type = IdType.NONE)
    private String id;

    /**
     * 本地地址
     */
    private String path;

    private Date gmtCreate;


}
