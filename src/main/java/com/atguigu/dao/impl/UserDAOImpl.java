package com.atguigu.dao.impl;

import com.atguigu.dao.UserDAO;
import com.atguigu.pojo.User;

/**
 * @author howardy
 * @date 2021/12/23 - 10:11
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from t_user where username = ?";
        User user = queryForOne(sql, username);
        return user;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryForOne(sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username, password, email) values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
