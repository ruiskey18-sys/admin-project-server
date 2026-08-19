package com.ruiskey.admin_project_server.controller;

import com.ruiskey.admin_project_server.common.Result;
import com.ruiskey.admin_project_server.dto.UserCreateDTO;
import com.ruiskey.admin_project_server.dto.UserPageDTO;
import com.ruiskey.admin_project_server.dto.UserUpdateDTO;
import com.ruiskey.admin_project_server.service.UserService;
import org.springframework.web.bind.annotation.*;


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


    @PostMapping("/pageList")
    public Result pageList(@RequestBody UserPageDTO userPageDTO){
        return Result.success(userService.pageList(userPageDTO));
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    public Result add(@RequestBody UserCreateDTO userCreateDTO) {
        return Result.success(userService.add(userCreateDTO));
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserUpdateDTO userUpdateDTO) {
        return Result.success(userService.update(userUpdateDTO));
    }

    /**
     * 获取用户详情
     */
    @GetMapping("/detail")
    public Result detail(@RequestParam String objectId) {
        return Result.success(userService.detail(objectId));
    }


    /**
     * 删除用户
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String objectId) {
        return Result.success(userService.delete(objectId));
    }


}
