package com.ruiskey.admin_project_server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_menu")
public class Menu {
    private String objectId;
    private Long parentId;
    private String name;
    private String path;
    private String component;
    private String title;
    private String icon;
    private String type;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
}
