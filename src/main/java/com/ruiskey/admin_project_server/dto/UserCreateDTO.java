package com.ruiskey.admin_project_server.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String userName;
    private String password;
    private String nickName;
}
