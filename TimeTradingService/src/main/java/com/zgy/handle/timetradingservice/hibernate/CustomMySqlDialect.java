package com.zgy.handle.timetradingservice.hibernate;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * @author: a4423
 * @date: 2020/9/26 20:32
 */
public class CustomMySqlDialect extends MySQL5Dialect {
    /**
     * 设置MySQL的编码格式为utf8mb4,可兼容表情符号
     * @return
     */
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci";
    }
}
