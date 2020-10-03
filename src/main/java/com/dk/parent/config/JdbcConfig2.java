package com.dk.parent.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConfig2 {

    @Bean
    public DataSource dataSource(JdbcProperties jdbc) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbc.getUrl());
        druidDataSource.setDriverClassName(jdbc.getDriverClassName());
        druidDataSource.setUsername(jdbc.getUsername());
        druidDataSource.setPassword(jdbc.getPassword());
        return druidDataSource;
    }
}
