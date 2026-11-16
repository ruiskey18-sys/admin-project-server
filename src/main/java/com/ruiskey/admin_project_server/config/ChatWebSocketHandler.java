package com.ruiskey.admin_project_server.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 保存所有连接的客户端
    private final Set<WebSocketSession> sessions =
            ConcurrentHashMap.newKeySet();

    // 连接建立成功
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("客户端连接：" + session.getId());
        System.out.println("当前在线连接数：" + sessions.size());
    }

    // 收到客户端消息
    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message) throws IOException {

        String content = message.getPayload();

        System.out.println("收到消息：" + content);

        // 将消息发送给所有在线客户端
        for (WebSocketSession client : sessions) {
            if (client.isOpen()) {
                client.sendMessage(new TextMessage(content));
            }
        }
    }

    // 连接关闭
    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status) {

        sessions.remove(session);
        System.out.println("客户端断开：" + session.getId());
    }
}
