package com.ww.spring.ioc.config.placeholder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.ww.spring.ioc.config.placeholder" })
@PropertySource(value = { "classpath:database-config.properties" }, ignoreResourceNotFound = true)
public class ApplicationConfig {

    /**
     * 定义了一个PropertySourcesPlaceholderConfigurer类的Bean，它的作用是为了让Spring能够解析属性占位符，比如这里既然属性文件已经定义了
     * 关于数据库连接所需要的配置，那么还需要知道如何去引用已经定义好的配置，这里可以使用注解@Value和占位符
     * 
     * 使用XML方式加载属性文件，location是一个配置属性文件路径的选项，它可以配置单个文件或多个文件，多个文件之间要使用逗号分隔；
     * <context:property-placeholder location="classpath:database-config.properties" />
     * 
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
