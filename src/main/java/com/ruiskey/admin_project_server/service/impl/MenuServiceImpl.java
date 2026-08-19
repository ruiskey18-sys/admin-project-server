package com.ruiskey.admin_project_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruiskey.admin_project_server.entity.Menu;
import com.ruiskey.admin_project_server.mapper.MenuMapper;
import com.ruiskey.admin_project_server.service.MenuService;
import com.ruiskey.admin_project_server.vo.MenuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;



    @Override
    public List<Menu> list(){
        List<Menu> menus = menuMapper.selectList(
                new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getStatus, 1)
                        .orderByAsc(Menu::getSort)
        );
        return menus;
    }


    @Override
    public List<MenuVO> listTree(){
        List<Menu> menus = menuMapper.selectList(
                new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getStatus, 1)
                        .orderByAsc(Menu::getSort)
        );
        List<MenuVO> menuTree = buildMenuTree(menus);
        return menuTree;
    }


    private List<MenuVO> buildMenuTree(List<Menu> menus) {
        // 1. Menu -> MenuVO
        List<MenuVO> menuVOList = menus.stream()
                .map(this::convertToMenuVO)
                .collect(Collectors.toList());

        // 2. 按 parentId 分组
        Map<String, List<MenuVO>> childrenMap = menuVOList.stream()
                .collect(Collectors.groupingBy(MenuVO::getParentId));

        // 3. 设置 children
        menuVOList.forEach(menuVO -> {
            List<MenuVO> children = childrenMap.get(menuVO.getObjectId());
            menuVO.setChildren(children);
        });

        // 4. 找出根节点
        return menuVOList.stream()
                .filter(menuVO ->
                        menuVO.getParentId() == null
                                || menuVO.getParentId().isEmpty()
                                || "0".equals(menuVO.getParentId())
                )
                .sorted(Comparator.comparing(
                        MenuVO::getSort,
                        Comparator.nullsLast(Integer::compareTo)
                ))
                .collect(Collectors.toList());
    }

    private MenuVO convertToMenuVO(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuVO menuVO = new MenuVO();
        menuVO.setObjectId(menu.getObjectId());
        menuVO.setParentId(menu.getParentId());
        menuVO.setName(menu.getName());
        menuVO.setPath(menu.getPath());
        menuVO.setComponent(menu.getComponent());
        menuVO.setTitle(menu.getTitle());
        menuVO.setIcon(menu.getIcon());
        menuVO.setType(menu.getType());
        menuVO.setSort(menu.getSort());
        menuVO.setStatus(menu.getStatus());
        menuVO.setCreateTime(menu.getCreateTime());
        return menuVO;
    }

}
