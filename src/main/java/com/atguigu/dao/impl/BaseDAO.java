package com.atguigu.dao.impl;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author howardy
 * @date 2021/12/22 - 21:12
 */
public abstract class BaseDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz =null;

    {
        // 获取当前BaseDAO的子类继承的父类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass(); // 注：这里的this指针指向的是子类的对象，所以其获取父类的泛型逻辑合理
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;

        // 获取了父类的泛型参数
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        // 泛型的第一个参数
        clazz = (Class<T>) actualTypeArguments[0];
    }

    /**
     * @Author HowardY
     * @Description 进行增删改操作
     * @Date 22:15 2021/12/22
     * @param sql
     * @param args
     * @return int 若出现异常，则返回-1
     **/
    public int update(String sql, Object ... args){
        try {
            Connection conn = JDBCUtils.getConnection();
            return queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public T queryForOne(String sql, Object ... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return (T) queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }

    public List<T> queryForList(String sql, Object ... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<T> query = (List<T>) queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }

    public Object queryForSingleValue(String sql, Object ... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        return null;
    }
}
