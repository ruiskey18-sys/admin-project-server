package com.ruiskey.admin_project_server.config;

import com.ruiskey.admin_project_server.service.UserService;
import com.ruiskey.admin_project_server.vo.UserDetailVO;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class UserMcpTools {

    private final UserService userService;

    public UserMcpTools(UserService userService) {
        this.userService = userService;
    }

    @McpTool(
            name = "getUserById",
            description = "根据用户ID查询用户信息"
    )
    public UserDetailVO getUserById(
            @McpToolParam(
                    description = "用户ID",
                    required = true
            )
            String objectId
    ) {
        return userService.detail(objectId);
    }
}
