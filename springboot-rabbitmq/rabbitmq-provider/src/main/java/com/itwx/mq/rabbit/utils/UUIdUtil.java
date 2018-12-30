package com.itwx.mq.rabbit.utils;

import java.util.UUID;

/**
 * @Author:wx
 * @Date:2018/12/23 15:40
 */
public class UUIdUtil {
    public static String getUUId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
