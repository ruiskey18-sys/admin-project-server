package com.ruiskey.admin_project_server.service;

import io.modelcontextprotocol.client.McpSyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Component;

import java.util.List;

/** 延迟到首次HTTP聊天请求时连接本服务，避免启动阶段自连接。 */
@Component
public class McpChatTools {
    private static final Logger log = LoggerFactory.getLogger(McpChatTools.class);
    private final List<McpSyncClient> clients;
    private ToolCallback[] callbacks;

    public McpChatTools(List<McpSyncClient> clients) {
        this.clients = clients;
    }

    public synchronized ToolCallback[] getToolCallbacks() {
        if (callbacks == null) {
            if (clients.isEmpty()) {
                throw new IllegalStateException("未配置MCP Client连接");
            }
            for (McpSyncClient client : clients) {
                if (!client.isInitialized()) {
                    client.initialize();
                }
            }
            // 不声明ToolCallbackProvider Bean，避免Server重新发布Client工具。
            ToolCallback[] discovered = new SyncMcpToolCallbackProvider(clients).getToolCallbacks();
            if (discovered.length == 0) {
                throw new IllegalStateException("MCP Server未返回任何工具");
            }
            callbacks = discovered;
            log.info("MCP tools/list完成，发现 {} 个远程工具", callbacks.length);
        }
        return callbacks.clone();
    }
}
