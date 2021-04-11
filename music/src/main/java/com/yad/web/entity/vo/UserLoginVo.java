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

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
