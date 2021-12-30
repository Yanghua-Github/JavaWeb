package atguigu.service.impl;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author howardy
 * @date 2021/12/23 - 15:05
 */
public class UserServiceImplTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void register() {
        userService.register(new User(101, "wjj101", "wjj101pswd", "wjj101@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login("wjj101", "wjj101pswd"));
    }

    @Test
    public void existUsername() {
        System.out.println(userService.existUsername("wjj101"));
    }
}