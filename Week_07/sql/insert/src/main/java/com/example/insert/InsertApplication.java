package com.example.insert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class InsertApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InsertApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;

    private String SQL = "insert into product_info(product_name,category,price)values(?,?,?)";

    @Override
    public void run(String... args) throws Exception {

        long startMillis = System.currentTimeMillis();

//        insert1();
        insertConcurrent();

        long endMillis = System.currentTimeMillis();
        System.out.printf("总共耗时%d\n", endMillis - startMillis);
    }

    /**
     * 单线程:
     * 1. 默认总共耗时350614
     * 2. 自增ID & 增加rewriteBatchedStatements=true:总共耗时10718
     * 3. 取消自增ID:总共耗时9379
     */
    private void insert1() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL);

            Random random = new Random();
            for (int i = 0; i < 100_0000; i++) {

                statement.setString(1, "product" + i);
                statement.setString(2, "分类" + i);
                statement.setDouble(3, random.nextInt(1000));
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 线程数8: 总共耗时5803
     * 线程数4: 总共耗时5512
     * 线程数2: 总共耗时6254
     *
     * @throws InterruptedException
     */
    private void insertConcurrent() throws InterruptedException {

        int threadNum = 4;
        //多线程并发写入
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int t = 0; t < threadNum; t++) {

            Thread thread = new Thread(() -> {
                try (Connection connection = dataSource.getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(SQL);

                    Random random = new Random();
                    for (int i = 0; i < 100_0000 / threadNum; i++) {

                        statement.setString(1, "product" + i);
                        statement.setString(2, "分类" + i);
                        statement.setDouble(3, random.nextInt(1000));
                        statement.addBatch();
                    }

                    statement.executeBatch();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });

            thread.start();
        }

        latch.await();
    }
}
