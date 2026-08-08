CREATE TABLE sys_user (
    object_id VARCHAR(50) PRIMARY KEY  NOT NULL COMMENT '业务唯一ID',

    username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录用户名',

    password VARCHAR(100) NOT NULL COMMENT '密码',

    nickname VARCHAR(50) COMMENT '用户昵称',

    real_name VARCHAR(50) COMMENT '真实姓名',

    avatar VARCHAR(255) COMMENT '头像地址',

    phone VARCHAR(20) COMMENT '手机号',

    email VARCHAR(100) COMMENT '邮箱',

    role VARCHAR(50) DEFAULT 'USER' COMMENT '角色',

    status TINYINT DEFAULT 1 COMMENT '状态 1启用 0禁用',

    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除 0正常 1删除',

    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'

) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='系统用户表';



CREATE TABLE sys_menu
(
    object_id VARCHAR(50) PRIMARY KEY  NOT NULL COMMENT '业务唯一ID',

    parent_id BIGINT DEFAULT 0 COMMENT '父菜单ID',

    name VARCHAR(50) NOT NULL COMMENT '路由名称',

    path VARCHAR(100) NOT NULL COMMENT '路由路径',

    component VARCHAR(100) COMMENT '组件名称',

    title VARCHAR(50) COMMENT '菜单标题',

    icon VARCHAR(50) COMMENT '图标',

    type VARCHAR(20) DEFAULT 'MENU' COMMENT '类型',

    sort INT DEFAULT 0 COMMENT '排序',

    status TINYINT DEFAULT 1 COMMENT '状态',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP

)
COMMENT='系统菜单表';


INSERT INTO sys_menu
(
 object_id,
parent_id,
name,
path,
component,
title,
icon,
sort
)
VALUES
(
'1',
0,
'User',
'/user',
'User',
'用户管理',
'user',
1
);


INSERT INTO sys_menu
(
 object_id,
parent_id,
name,
path,
component,
title,
icon,
sort
)
VALUES
(
 '2',
0,
'Project',
'/project',
'Project',
'项目管理',
'project',
2
);