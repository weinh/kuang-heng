package java_knowledge.advanced;

/**
 * <p>类 名 称：TestFunction.java
 * <p>功能说明：
 * <p>开发时间：2018年06月05日
 *
 * @author ：weinh
 */
@FunctionalInterface
interface MyFunction {
    default void defaultFunction() {
    }

    static void staticFunction() {
    }

    @Override
    boolean equals(Object object);

    void only(String value);
}

public class TestFunction {
    public static void println(String string, MyFunction myFunction) {
        myFunction.only(string);
    }

    public static void main(String[] args) {
        String string = "test";
        println(string, (x) -> {
            System.out.println(x);
        });
    }

//    public static void main(String[] args) {
//        String string = "test";
//        println(string, new TestFunction().new $Lambda$1());
//    }
//
//    /**
//     * 编译器生成的私有静态方法
//     *
//     * @param string 参数
//     */
//    private static void lambda$main$0(String string) {
//        System.out.println(string);
//    }
//
//    /**
//     * 执行生成的私有静态方法，创建的内部类
//     */
//    final class $Lambda$1 implements MyFunction {
//        @Override
//        public void only(String string) {
//            lambda$main$0(string);
//        }
//    }
}