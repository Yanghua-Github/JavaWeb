package atguigu.utils;

import com.atguigu.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author howardy
 * @date 2021/12/23 - 10:27
 */
public class JDBCUtilsTest {

    @Test
    public void getConnection() {
        try {
            Connection conn = JDBCUtils.getConnection();
            System.out.println("conn = " + conn);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void closeResource() {
    }

    @Test
    public void testCloseResource() {
    }
}