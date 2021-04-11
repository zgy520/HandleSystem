package com.zgy.handle.replayservice.config.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @author: a4423
 * @date: 2020/9/26 20:25
 */
public class ReplayNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    /**
     * 生成的表名前缀
      */
    private final static String TABLE_PREFIX = "replay_";
    /**
     * 自定义创建的表名的前缀
     * @param name
     * @param context
     * @return
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier(TABLE_PREFIX + name.getText(), name.isQuoted());
    }
}
