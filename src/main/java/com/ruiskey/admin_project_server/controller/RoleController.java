package com.ruiskey.admin_project_server.controller;

import com.ruiskey.admin_project_server.vo.RolePageVO;
import com.ruiskey.admin_project_server.dto.RoleCreateDTO;
import com.ruiskey.admin_project_server.dto.RolePageDTO;
import com.ruiskey.admin_project_server.dto.RoleUpdateDTO;
import com.ruiskey.admin_project_server.entity.Role;
import com.ruiskey.admin_project_server.common.Result;
import com.ruiskey.admin_project_server.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public Result<List<Role>> list() {
        return Result.success(roleService.list());
    }

    @PostMapping("/pageList")
    public Result<RolePageVO> pageList(@RequestBody RolePageDTO dto) {
        return Result.success(roleService.pageList(dto));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody RoleCreateDTO dto) {
        roleService.add(dto);
        return Result.success(null);
    }

    @PostMapping("/update")
    public Result<Void> update(@RequestBody RoleUpdateDTO dto) {
        roleService.update(dto);
        return Result.success(null);
    }

    @GetMapping("/detail")
    public Result<Role> detail(@RequestParam String objectId) {
        return Result.success(roleService.detail(objectId));
    }

    @GetMapping("/delete")
    public Result<Void> delete(@RequestParam String objectId) {
        roleService.delete(objectId);
        return Result.success(null);
    }
}
