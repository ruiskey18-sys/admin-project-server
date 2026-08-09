package com.ruiskey.admin_project_server.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private String nickname;
}
