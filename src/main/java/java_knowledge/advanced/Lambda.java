package java_knowledge.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>类 名 称：Lambda.java
 * <p>功能说明：
 * <p>开发时间：2018年06月04日
 *
 * @author ：weinh
 */
public class Lambda {
    private String scope = "";

    public void Syntax() {
        List<String> list = new ArrayList<>();
        list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);
        list.forEach(s -> {
            System.out.println(s);
        });
        new Thread(() -> System.out.println(scope));
    }

    public void testThis() throws InterruptedException {
        String param = "";
        Thread thread = new Thread(() -> {
            // 错误 不能重复定义
            // String param = "";
            System.out.println(this);
        });
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        Lambda lambda = new Lambda();
        lambda.testThis();
    }
}
