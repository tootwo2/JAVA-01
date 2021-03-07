package com.example.split1;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置数据源
 *
 * @Author tootwo2
 * @Date 2021/3/7 10:28 下午
 */
@Configuration
public class DataSourceConfig {

    @Bean("dataSource")
    @Primary
    public DataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> target = new HashMap<>();
        target.put("dataSource1", dataSource1());
        target.put("dataSource2", dataSource2());
        dataSource.setTargetDataSources(target);
        dataSource.setDefaultTargetDataSource(dataSource1());

        return dataSource;
    }

    public DataSource dataSource1() {
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("12345678");
        return dataSource1;
    }

    public DataSource dataSource2() {
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test2?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("12345678");
        return dataSource1;
    }

}
