package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @author howardy
 * @date 2021/12/23 - 10:01
 */
public interface UserDAO {

    /**
     * @Author HowardY
     * @Description 根据用户名查询是否存在该姓名的用户
     * @Date 10:03 2021/12/23
     * @param username
     * @return com.atguigu.pojo.User
     **/
    public User queryUserByUsername(String username);

    /**
     * @Author HowardY
     * @Description 根据username以及password判断是否存在对应的用户，不存在则返回null
     * @Date 10:07 2021/12/23
     * @param username
     * @param password
     * @return com.atguigu.pojo.User
     **/
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * @Author HowardY
     * @Description 保存User对象到数据库中
     * @Date 10:10 2021/12/23
     * @param user
     * @return int
     **/
    public int saveUser(User user);
}
