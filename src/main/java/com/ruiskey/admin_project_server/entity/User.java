package com.ruiskey.admin_project_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统用户表
 */
@Data
@TableName("sys_user")
public class User {

    /**
     * 业务唯一ID
     */
    @TableId(
            value = "object_id",
            type = IdType.ASSIGN_ID
    )
    private String objectId;


    /**
     * 登录用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 用户昵称
     */
    private String nickname;


    /**
     * 真实姓名
     */
    private String realName;


    /**
     * 头像
     */
    private String avatar;


    /**
     * 手机号
     */
    private String phone;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 角色
     */
    private String role;


    /**
     * 状态
     * 1 启用
     * 0 禁用
     */
    private Integer status;


    /**
     * 删除标记
     * 0 正常
     * 1 删除
     */
    private Integer deleted;


    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

}
