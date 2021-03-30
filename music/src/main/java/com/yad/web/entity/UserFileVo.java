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
}
