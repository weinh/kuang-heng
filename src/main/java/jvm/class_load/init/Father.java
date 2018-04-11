package jvm.class_load.init;

/**
 * 类 名 称：Father.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月11日
 */
public class Father {
    private final static int i = 1;
    private static int j = 2;
    private int k = 3;

    {
        System.out.println("father i=" + i + ",j=" + j + ",k=" + k);
        j = 4;
        k = 5;
    }

    static {
        System.out.println("father i=" + i + ",j=" + j);
        j = 6;
    }

    public Father() {
        System.out.println("father i=" + i + ",j=" + j + ",k=" + k);
        j = 8;
        k = 9;
    }
}
