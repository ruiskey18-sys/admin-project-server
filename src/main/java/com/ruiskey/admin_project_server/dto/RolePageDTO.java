package com.ruiskey.admin_project_server.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class RolePageDTO {
    @JsonAlias("index")
    private Integer pageNum = 1;
    @JsonAlias("size")
    private Integer pageSize = 10;
    @JsonAlias("key")
    private String roleCode;
    @JsonAlias("name")
    private String roleName;
    private Integer status;
}
