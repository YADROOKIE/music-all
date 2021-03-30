package com.yad.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseMusic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 音乐名称
     */
    private String musicName;

    /**
     * 音乐类型
     */
    private String type;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 音乐封面
     */
    private String cover;

    private String description;

    /**
     * 歌词
     */
    private String lyrics;

    /**
     * 歌曲时长
     */
    private String musicLen;

    /**
     * 存在本地的路径
     */
    private String filePath;

    private Date createAt;


}
