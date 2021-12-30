package com.atguigu.service.impl;

import com.atguigu.dao.impl.UserDAOImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @author howardy
 * @date 2021/12/23 - 14:59
 */
public class UserServiceImpl implements UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void register(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User login(String username, String password) {
        return userDAO.queryUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean existUsername(String username) {
        if(userDAO.queryUserByUsername(username) != null){
            return true;
        }
        return false;
    }
}
