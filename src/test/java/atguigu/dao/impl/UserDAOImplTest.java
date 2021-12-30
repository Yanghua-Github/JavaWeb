package atguigu.dao.impl;

import com.atguigu.dao.impl.UserDAOImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author howardy
 * @date 2021/12/23 - 10:21
 */
public class UserDAOImplTest {
    private UserDAOImpl user = new UserDAOImpl();

    @Test
    public void queryUserByUsername() {
        System.out.println(user.queryUserByUsername("ldd1013"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(user.queryUserByUsernameAndPassword("ldd1013", "ldd1013pswd"));
    }

    @Test
    public void saveUser() {
        User userVal = new User(101, "howardy", "19981012", "howardy@qq.com");
        System.out.println(user.saveUser(userVal));
    }
}