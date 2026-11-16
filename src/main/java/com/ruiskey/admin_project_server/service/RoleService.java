package com.ruiskey.admin_project_server.service;

import com.ruiskey.admin_project_server.vo.RolePageVO;
import com.ruiskey.admin_project_server.dto.RoleCreateDTO;
import com.ruiskey.admin_project_server.dto.RolePageDTO;
import com.ruiskey.admin_project_server.dto.RoleUpdateDTO;
import com.ruiskey.admin_project_server.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<Role> {
    void add(RoleCreateDTO dto);

    void update(RoleUpdateDTO dto);

    Role detail(String objectId);

    void delete(String objectId);

    RolePageVO pageList(RolePageDTO dto);
}
