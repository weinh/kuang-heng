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
     * -Xmx5m
     *
     * @param count 几兆
     * @return
     */
    private static Byte[] dumpOOM(int count) {
        Byte b[] = new Byte[size * count];
        return b;
    }

    public static void main(String[] args) throws InterruptedException {
        Byte b[] = dumpOOM(10);
//        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        try {
            stackOverFlowError();
        } catch (Error e) {
            e.printStackTrace();
        }
    }


}

