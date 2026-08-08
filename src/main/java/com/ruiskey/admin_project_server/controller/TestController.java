package com.ruiskey.admin_project_server.controller;

import com.ruiskey.admin_project_server.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public Result hello() {
        return Result.success("hello spring boot");
    }
}
