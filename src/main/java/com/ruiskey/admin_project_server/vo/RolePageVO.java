package com.ruiskey.admin_project_server.vo;

import java.time.LocalDateTime;
import java.util.List;

public record RolePageVO(long current, long size, List<Row> records, long total, long pages) {
    public record Row(String id, String name, String key, boolean status, List<String> permiss,
                      LocalDateTime createdTime, LocalDateTime updatedTime) {}
}
