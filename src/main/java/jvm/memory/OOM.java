package jvm.memory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类 名 称：OOM.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月08日
 */
public class OOM {

    private static int size = 1024 * 1024;
    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * -Xmx5m
     *
     * @param count
     * @return
     */
    private static Byte[] dumpOOM(int count) {
        Byte b[] = new Byte[size * count];
        return b;
    }

    public static void main(String[] args) throws InterruptedException {
//        Byte b[] = dumpOOM(10);
//        b = null;
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        count.incrementAndGet();
        try {
            stackOverFlowError();
        } catch (Error e) {
            System.err.println(count.get());
            e.printStackTrace();
        }
    }


}

