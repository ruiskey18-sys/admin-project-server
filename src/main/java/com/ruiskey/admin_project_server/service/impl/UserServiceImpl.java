package com.ruiskey.admin_project_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiskey.admin_project_server.common.IdGenerator;
import com.ruiskey.admin_project_server.dto.UserCreateDTO;
import com.ruiskey.admin_project_server.dto.UserUpdateDTO;
import com.ruiskey.admin_project_server.entity.User;
import com.ruiskey.admin_project_server.mapper.UserMapper;
import com.ruiskey.admin_project_server.service.UserService;
import com.ruiskey.admin_project_server.vo.UserDetailVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Object add(UserCreateDTO userCreateDTO) {
        User user = new User();
        // 入参转entity
        user.setObjectId(IdGenerator.generateId());
        user.setUsername(userCreateDTO.getUsername());
        user.setNickname(userCreateDTO.getNickname());
        user.setPassword(userCreateDTO.getPassword());
        // 存储
        this.save(user);
        return null;
    }

    @Override
    public Object update(UserUpdateDTO userUpdateDTO) {
        this.update(null, new LambdaUpdateWrapper<User>().eq(User::getObjectId, userUpdateDTO.getObjectId())
                .set(User::getUsername, userUpdateDTO.getUsername()));
        return null;
    }

    @Override
    public UserDetailVO detail(String objectId) {
        UserDetailVO userDetailVO = new UserDetailVO();
        User user = this.getById(objectId);
        userDetailVO.setObjectId(user.getObjectId());
        userDetailVO.setUserName(user.getUsername());
        return userDetailVO;
    }

    @Override
    public Object delete(String objectId) {
        this.removeById(objectId);
        return null;
    }
}
