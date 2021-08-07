package com.ww.spring.ioc.config.placeholder;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataSourceBean {

    @Value("${jdbc.database.driver}")
    private String driver = null;
    
    @Value("${jdbc.database.url}")
    private String url = null;
    
    @Value("${jdbc.database.username}")
    private String username = null;
    
    @Value("${jdbc.database.password}")
    private String password = null;
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", driver);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dataSource;
    }
}
