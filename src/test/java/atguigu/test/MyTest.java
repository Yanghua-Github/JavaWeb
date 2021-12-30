package atguigu.test;

import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author howardy
 * @date 2021/12/28 - 20:30
 */
public class MyTest {
    /**
     * 测试遍历是否为引用
     */
    @Test
    public void test1(){
        Map<Integer, CartItem> items = new LinkedHashMap<>();
        items.put(1, new CartItem("java", 1, new BigDecimal(3), 1));
        items.put(2, new CartItem("c++", 2, new BigDecimal(3), 1));
        items.put(3, new CartItem("c#", 3, new BigDecimal(3), 1));

        items.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
        for(Map.Entry<Integer, CartItem> entry : items.entrySet()){
            entry.getValue().setCount(101);
        }
        items.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
    }

    /**
     * 测试Date函数
     */
    @Test
    public void test2() throws ParseException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        format.setTimeZone(TimeZone.getDefault());
        System.out.println(format.format(timestamp));
    }
}
