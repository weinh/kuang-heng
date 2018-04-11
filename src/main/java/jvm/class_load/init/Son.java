package jvm.class_load.init;

/**
 * 类 名 称：Son.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月11日
 */
public class Son extends Father {
    private final static int i = 1;
    private static int j = 2;
    private int k = 3;

    {
        System.out.println("son i=" + i + ",j=" + j + ",k=" + k);
        j = 4;
        k = 5;
    }

    static {
        System.out.println("son i=" + i + ",j=" + j);
        j = 6;
    }

    public Son() {
        System.out.println("son i=" + i + ",j=" + j + ",k=" + k);
        j = 8;
        k = 9;
    }

    public static void main(String[] args) {
        new Son();
    }
}
