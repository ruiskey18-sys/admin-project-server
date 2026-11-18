package com.ruiskey.admin_project_server.config;

import com.ruiskey.admin_project_server.service.UserService;
import com.ruiskey.admin_project_server.vo.UserDetailVO;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserMcpTools {
    private static final Logger log = LoggerFactory.getLogger(UserMcpTools.class);

    private final UserService userService;

    public UserMcpTools(UserService userService) {
        this.userService = userService;
    }

    @McpTool(
            name = "getUserById",
            description = "根据用户明确提供的用户ID查询用户信息。不能通过姓名查询，也不能猜测ID。返回null表示用户不存在。"
    )
    public UserDetailVO getUserById(
            @McpToolParam(
                    description = "用户明确提供的业务ID，按字符串原样传递",
                    required = true
            )
            String objectId
    ) {
        if (!StringUtils.hasText(objectId)) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        log.info("MCP Server执行工具 getUserById");
        return userService.detail(objectId);
    }
}
