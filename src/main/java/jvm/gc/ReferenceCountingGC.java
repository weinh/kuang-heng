package jvm.gc;

/**
 * 类 名 称：ReferenceCountingGC.java
 * 功能说明：循环引用计数法GC回收情况
 * 开发人员：weinh
 * 开发时间：2018年04月24日
 */
public class ReferenceCountingGC {
    private byte b[] = new byte[1024 * 1024 * 2];
    private ReferenceCountingGC instance;

    /**
     * 运行时，加入jvm参数查看GC情况：-XX:+PrintGCDetails
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ReferenceCountingGC refA = new ReferenceCountingGC();
        ReferenceCountingGC refB = new ReferenceCountingGC();
        refA.instance = refB;
        refB.instance = refA;

        refA = null;
        refB = null;
        System.gc();
    }
}
