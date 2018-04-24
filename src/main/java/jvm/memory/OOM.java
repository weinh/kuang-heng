package jvm.memory;

/**
 * 类 名 称：OOM.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月08日
 */
public class OOM {

    private static int size = 1024 * 1024;

    /**
     * 设置最大堆内存为5M，-Xmx5m，程序分配10M内存
     *
     * @param count 几兆
     * @return
     */
    private static Byte[] heapOOM(int count) {
        Byte b[] = new Byte[size * count];
        return b;
    }

    public static void main(String[] args) throws InterruptedException {
        Byte b[] = heapOOM(10);
//        stackOverFlowError();
    }

    /**
     * 栈移除
     */
    private static void stackOverFlowError() {
        try {
            stackOverFlowError();
        } catch (Error e) {
            e.printStackTrace();
        }
    }


}

