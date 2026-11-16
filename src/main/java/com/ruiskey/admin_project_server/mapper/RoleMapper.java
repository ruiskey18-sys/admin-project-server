package com.ruiskey.admin_project_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruiskey.admin_project_server.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    record Permission(String roleId, String permissionId) {}

    @Select("""
            <script>
            SELECT role_id AS roleId, permission_id AS permissionId
            FROM sys_role_permission
            WHERE role_id IN
            <foreach collection="roleIds" item="id" open="(" separator="," close=")">
                #{id}
            </foreach>
            ORDER BY role_id, permission_id
            </script>
            """)
    List<Permission> findPermissions(@Param("roleIds") List<String> roleIds);
}
