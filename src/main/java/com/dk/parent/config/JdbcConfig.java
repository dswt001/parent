package com.dk.parent.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 传统java配置:jdbc配置
 * @<code>@Configuration</code>声明JdbcConfig是一个配置类
 * @<code>@PropertySource</code>指定属性文件的路径是：classpath:jdbc.properties
 * 通过<code>@Value</code>为属性注入值
 * 通过<code>@Bean</code>将<code>dataSource</code>方法声明为一个注册Bean的方法，Spring会自动调用该方法，
 * 将方法的返回值加入Spring容器中。
 * 这样我们就可以在任意位置通过<code>@Autowired</code>注入<code>DataSource</code>了。
 *
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {

    @Value("${jdbc.url}")
    String url;

    @Value("${jdbc.driverClassName}")
    String driverClassName;

    @Value("${jdbc.username}")
    String username;

    @Value("jdbc.password")
    String password;

    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }
}
