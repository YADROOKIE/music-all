package com.yad.web.entity.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private String stuNo;
    private String password;

    public UserLoginVo(String stuNo, String password) {
        this.stuNo = stuNo;
        this.password =password;
    }
}
