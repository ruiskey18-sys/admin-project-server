package com.ruiskey.admin_project_server.dto;

import lombok.Data;

@Data
public class RoleCreateDTO {
    private String roleCode;
    private String roleName;
    private Integer status;
}
