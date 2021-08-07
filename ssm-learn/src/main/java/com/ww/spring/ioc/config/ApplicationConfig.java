package com.ww.spring.ioc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:database-config.properties" }, ignoreResourceNotFound = true)
public class ApplicationConfig {

}
