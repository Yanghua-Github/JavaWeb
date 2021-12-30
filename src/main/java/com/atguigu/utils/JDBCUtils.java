package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author howardy
 * @date 2021/12/22 - 21:49
 */
public class JDBCUtils {
    private static DruidDataSource dataSource = null;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static{
        try {
            Properties pros = new Properties();
            // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // version 1.0：未考虑事务
//    public static Connection getConnection() throws SQLException {
//        DruidPooledConnection connection = dataSource.getConnection();
//        return connection;
//    }

    // version2.0: 考虑事务
    public static Connection getConnection() {
        Connection conn = conns.get();
        try {
            if(conn == null){
                conn = dataSource.getConnection(); //从数据库连接池中获取连接
                conns.set(conn);  // 保存到 ThreadLocal 对象中，供后面的 jdbc 操作
                conn.setAutoCommit(false); // 设置为手动管理事务
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    // version 1.0：未考虑事务
//    public static void closeResource(Connection conn, Statement st){
//        try {
//            if(conn != null)
//                conn.close();
//            if(st != null)
//                st.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
    // version 1.0：未考虑事务
//    public static void closeResource(Connection conn, Statement st, ResultSet rs){
//        try {
//            if(conn != null)
//                conn.close();
//            if(st != null)
//                st.close();
//            if(rs != null)
//                rs.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    // version2.0 : 考虑事务
    public static void closeAndCommit(){
        Connection conn = conns.get();
        // 提交事务
        try {
            conn.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            // 关闭连接
            try {
                conn.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    // version2.0 : 考虑事务
    public static void closeAndRollback(){
        Connection conn = conns.get();
        // 提交事务
        try {
            conn.rollback();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            // 关闭连接
            try {
                conn.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }
}
