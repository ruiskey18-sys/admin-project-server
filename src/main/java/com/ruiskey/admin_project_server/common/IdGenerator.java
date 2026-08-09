package com.ruiskey.admin_project_server.common;

import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static String generateId() {
        return LocalDateTime.now().format(DATE_FORMATTER) + IdUtil.nanoId(6);
    }
}
