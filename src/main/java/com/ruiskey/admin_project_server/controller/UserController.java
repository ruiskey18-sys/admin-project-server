package com.ruiskey.admin_project_server.controller;

import com.ruiskey.admin_project_server.common.Result;
import com.ruiskey.admin_project_server.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    public Result list(){
        return Result.success(userService.list());

    }


}
