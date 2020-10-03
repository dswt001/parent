package com.dk.parent.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SpringBoot的属性注入
 * java配置方式通过属性注入使用的是<code>@Value</code>注解。这种方式虽然可行，但是它只能注入基本类型值。而在SpringBoot中，
 * 提供了一种新的属性注入方式，支持各种java基本数据类型及复杂类型的注入。
 * 在类上通过<code>@ConfigurationProperties</code>注解声明当前类为属性读取类。
 * <code>prefix="jdbc"</code>读取属性文件中前缀为jdbc的值。
 * 在类上定义各个属性，名称必须与属性文件中jdbc.后面部分一致
 * 需要注意的是，这里我们并没有指定属性文件的地址，所以我们需要把jdbc.properties里的配置放在application.properties里，
 * 这是SpringBoot默认读取的属性文件名.
 *
 */
//@Data
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {

    private String url;

    private String driverClassName;

    private String username;

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JdbcProperties{" +
                "url='" + url + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
