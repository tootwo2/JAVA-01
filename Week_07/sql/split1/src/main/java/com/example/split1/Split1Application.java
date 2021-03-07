package com.example.split1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Split1Application implements CommandLineRunner {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Split1Application.class, args);
    }

    @Autowired
    DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {

        select();
        //切换数据源
        DynamicDataSourceHolder.putDataSource("dataSource2");
        select();
    }

    private void select() {
        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(1) ct from product_info limit 100");

            //查询计算行数
            int ct = 0;
            while (rs.next()) {  //通过next来索引：判断是否有下一个记录

                ct = rs.getInt(1);  //方法：int java.sql.ResultSet.getInt(int columnIndex) throws SQLException

            }

            System.out.println("行数:" + ct);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
