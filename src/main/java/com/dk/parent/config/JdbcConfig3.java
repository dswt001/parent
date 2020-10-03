package com.dk.parent.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 第三种注入jdbc方式：springboot推荐的更简单优雅的注入
 * 通过@EnableConfigurationProperties(JdbcProperties.class)来声明要使用JdbcProperties这个类的对象
 * 我们直接把@ConfigurationProperties(prefix = “jdbc”)声明在需要使用的@Bean的方法上，然后SpringBoot就会自动调用这个Bean
 * （此处是DataSource）的set方法，然后完成注入。使用的前提是：该类必须有对应属性的set方法！ 就是讲解二中的JdbcProperties类
 */
@Configuration
public class JdbcConfig3 {

//    @Bean
//    加上下面的注入，会因为在JdbcProperties中已经添加了，会报错，说重复
//    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

}
