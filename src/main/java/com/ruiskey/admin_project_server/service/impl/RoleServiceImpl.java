package com.ruiskey.admin_project_server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruiskey.admin_project_server.vo.RolePageVO;
import com.ruiskey.admin_project_server.dto.RoleCreateDTO;
import com.ruiskey.admin_project_server.dto.RolePageDTO;
import com.ruiskey.admin_project_server.dto.RoleUpdateDTO;
import com.ruiskey.admin_project_server.entity.Role;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiskey.admin_project_server.common.IdGenerator;
import com.ruiskey.admin_project_server.mapper.RoleMapper;
import com.ruiskey.admin_project_server.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public void add(RoleCreateDTO dto) {
        Role role = new Role();
        role.setObjectId(IdGenerator.generateId());
        role.setRoleCode(dto.getRoleCode());
        role.setRoleName(dto.getRoleName());
        role.setStatus(dto.getStatus());
        save(role);
    }

    @Override
    public void update(RoleUpdateDTO dto) {
        if (!StringUtils.hasText(dto.getObjectId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "缺少角色ID");
        }
        if (dto.getRoleCode() == null && dto.getRoleName() == null && dto.getStatus() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "缺少需要更新的字段");
        }
        Role role = new Role();
        role.setObjectId(dto.getObjectId());
        role.setRoleCode(dto.getRoleCode());
        role.setRoleName(dto.getRoleName());
        role.setStatus(dto.getStatus());
        if (!updateById(role)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "角色不存在");
        }
    }

    @Override
    public Role detail(String objectId) {
        Role role = getById(objectId);
        if (role == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "角色不存在");
        }
        return role;
    }

    @Override
    public void delete(String objectId) {
        if (!removeById(objectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "角色不存在");
        }
    }

    @Override
    public RolePageVO pageList(RolePageDTO dto) {
        String roleCode = dto.getRoleCode();
        String roleName = dto.getRoleName();
        boolean hasCode = StringUtils.hasText(roleCode);
        boolean hasName = StringUtils.hasText(roleName);
        LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<Role>()
                .and(hasCode || hasName, group -> group
                        .like(hasCode, Role::getRoleCode, roleCode)
                        .or(hasCode && hasName)
                        .like(hasName, Role::getRoleName, roleName))
                .eq(dto.getStatus() != null, Role::getStatus, dto.getStatus())
                .orderByDesc(Role::getCreatedTime)
                .orderByDesc(Role::getObjectId);
        Page<Role> page = page(new Page<>(dto.getPageNum(), dto.getPageSize()), query);
        if (page.getRecords().isEmpty()) {
            return new RolePageVO(page.getCurrent(), page.getSize(), List.of(), page.getTotal(), page.getPages());
        }

        List<String> roleIds = page.getRecords().stream().map(Role::getObjectId).toList();
        Map<String, List<String>> permissions = baseMapper.findPermissions(roleIds).stream()
                .collect(Collectors.groupingBy(RoleMapper.Permission::roleId,
                        Collectors.mapping(RoleMapper.Permission::permissionId, Collectors.toList())));
        List<RolePageVO.Row> records = page.getRecords().stream()
                .map(role -> new RolePageVO.Row(role.getObjectId(), role.getRoleName(), role.getRoleCode(),
                        Integer.valueOf(1).equals(role.getStatus()),
                        permissions.getOrDefault(role.getObjectId(), List.of()),
                        role.getCreatedTime(), role.getUpdatedTime()))
                .toList();
        return new RolePageVO(page.getCurrent(), page.getSize(), records, page.getTotal(), page.getPages());
    }
}
