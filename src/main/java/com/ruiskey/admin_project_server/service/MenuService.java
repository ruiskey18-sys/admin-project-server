package com.ruiskey.admin_project_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruiskey.admin_project_server.entity.Menu;
import com.ruiskey.admin_project_server.vo.MenuVO;

import java.util.List;


public interface MenuService extends IService<Menu> {

    List<Menu> list();

    List<MenuVO> listTree();
}
