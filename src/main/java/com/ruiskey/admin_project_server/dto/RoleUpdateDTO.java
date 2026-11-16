package com.ruiskey.admin_project_server.dto;

import lombok.Data;

@Data
public class RoleUpdateDTO {
    private String objectId;
    private String roleCode;
    private String roleName;
    private Integer status;
}
