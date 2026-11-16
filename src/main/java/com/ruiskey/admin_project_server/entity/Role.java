package com.ruiskey.admin_project_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_role")
public class Role {
    @TableId(value = "object_id", type = IdType.INPUT)
    private String objectId;
    private String roleCode;
    private String roleName;
    private Integer status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
