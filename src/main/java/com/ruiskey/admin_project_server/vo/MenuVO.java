package com.ruiskey.admin_project_server.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuVO {
    private String objectId;
    private String parentId;
    private String name;
    private String path;
    private String component;
    private String title;
    private String icon;
    private String type;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private List<MenuVO> children;
}
