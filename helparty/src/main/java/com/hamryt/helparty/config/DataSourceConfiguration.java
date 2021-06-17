package com.hamryt.helparty.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/db-secrets.properties")
public class DataSourceConfiguration {
    
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(
        @Qualifier(value = "masterDataSource") DataSource masterDataSource,
        @Qualifier(value = "slaveDataSource") DataSource slaveDataSource) {
        
        AbstractRoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave", slaveDataSource);
        
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        
        return routingDataSource;
    }
    
    @Bean(name = "proxyDataSource")
    public DataSource proxyDataSource(
        @Qualifier(value = "routingDataSource") DataSource routingDataSource) {
        
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier(value = "proxyDataSource") DataSource dataSource){
    
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        
        return transactionManager;
    }
    
}
