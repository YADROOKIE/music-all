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
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 1L;
    public  BaseUser(){}
    public BaseUser(String name, String stuNo, Integer age, String password) {
        this.name = name;
        this.stuNo = stuNo;
        this.age = age;
        this.password = password;
    }
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 学号
     */
    private String stuNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    private Date createAt;

    private Date updateAt;


}
