package com.yad.web.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserFileVo {
    private String id;
    private String parent;
    private String type;

    private String name;

    private String fId;

    List<UserFile> children ;

    private Date gmtCreate;

    private Date gmtUpdate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public List<UserFile> getChildren() {
		return children;
	}

	public void setChildren(List<UserFile> children) {
		this.children = children;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}
    
}
