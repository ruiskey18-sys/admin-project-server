package com.ruiskey.admin_project_server.controller;

import com.ruiskey.admin_project_server.common.Result;
import com.ruiskey.admin_project_server.service.AiChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ai")
public class AiChatController {
    private static final Logger log = LoggerFactory.getLogger(AiChatController.class);
    private final AiChatService chatService;

    public AiChatController(AiChatService chatService) {
        this.chatService = chatService;
    }

    public record ChatRequest(String message) {}
    public record ChatResponse(String answer) {}

    @PostMapping("/chat")
    public Result<ChatResponse> chat(@RequestBody ChatRequest request) {
        if (!StringUtils.hasText(request.message()) || request.message().length() > 4000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message不能为空且不能超过4000个字符");
        }
        try {
            String answer = chatService.chat(request.message());
            if (!StringUtils.hasText(answer)) {
                throw new IllegalStateException("模型未返回回答");
            }
            return Result.success(new ChatResponse(answer));
        } catch (RuntimeException ex) {
            log.error("AI聊天调用失败", ex);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "查询或模型调用失败，请检查Ollama及数据库服务", ex);
        }
    }
}
