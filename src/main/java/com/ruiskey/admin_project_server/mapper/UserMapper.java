package com.ruiskey.admin_project_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruiskey.admin_project_server.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
