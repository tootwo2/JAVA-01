package jdbc;

import java.sql.*;

/**
 * 原生接口
 *
 * @Author tootwo2
 * @Date 2021/2/21 12:25 上午
 */
public class JdbcDemo1 {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.4.93:3306/scmtest?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "omuser";
    static final String PASS = "omuser";

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "select a1,a2 from tmp";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索

                String a1 = rs.getString("a1");

                // 输出数据
                System.out.println(a1);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("close!");
    }
}
