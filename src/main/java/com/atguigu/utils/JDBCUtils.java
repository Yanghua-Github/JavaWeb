package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
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

    public static Connection getConnection() throws SQLException {
        DruidPooledConnection connection = dataSource.getConnection();
        return connection;
    }

    public static void closeResource(Connection conn, Statement st){
        try {
            if(conn != null)
                conn.close();
            if(st != null)
                st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement st, ResultSet rs){
        try {
            if(conn != null)
                conn.close();
            if(st != null)
                st.close();
            if(rs != null)
                rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
