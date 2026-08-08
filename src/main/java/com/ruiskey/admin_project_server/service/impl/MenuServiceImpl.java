package com.ruiskey.admin_project_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiskey.admin_project_server.entity.Menu;
import com.ruiskey.admin_project_server.mapper.MenuMapper;
import com.ruiskey.admin_project_server.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;



    @Override
    public List<Menu> list(){

        return menuMapper.selectList(
                new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getStatus,1)
                        .orderByAsc(Menu::getSort)
        );

    }
}
