package com.ruiskey.admin_project_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiskey.admin_project_server.entity.User;
import com.ruiskey.admin_project_server.mapper.UserMapper;
import com.ruiskey.admin_project_server.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
