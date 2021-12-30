package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author howardy
 * @date 2021/12/23 - 14:45
 */
public interface UserService {
    /*
    1.注册
    2.登录
     */

    /**
     * @Author HowardY
     * @Description 注册用户user到数据库中
     * @Date 14:56 2021/12/23
     * @param user
     **/
    void register(User user);
    
    /**
     * @Author HowardY
     * @Description 检测登录的用户名和密码是否正确，若不正确，则返回null
     * @Date 14:57 2021/12/23
     * @param username
     * @param password
     * @return com.atguigu.pojo.User
     **/
    User login(String username, String password);

    /**
     * @Author HowardY
     * @Description 通过username检查数据库中是否存在重复，若重复，则返回true
     * @Date 14:57 2021/12/23
     * @param username
     * @return boolean
     **/
    boolean existUsername(String username);
}
