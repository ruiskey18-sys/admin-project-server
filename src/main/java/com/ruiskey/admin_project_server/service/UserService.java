package com.ruiskey.admin_project_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruiskey.admin_project_server.dto.UserCreateDTO;
import com.ruiskey.admin_project_server.dto.UserPageDTO;
import com.ruiskey.admin_project_server.dto.UserUpdateDTO;
import com.ruiskey.admin_project_server.entity.User;
import com.ruiskey.admin_project_server.vo.UserDetailVO;

public interface UserService extends IService<User> {
    Object add(UserCreateDTO userCreateDTO);

    Object update(UserUpdateDTO userUpdateDTO);

    UserDetailVO detail(String objectId);

    Object delete(String objectId);

    Object pageList(UserPageDTO userPageDTO);
}
