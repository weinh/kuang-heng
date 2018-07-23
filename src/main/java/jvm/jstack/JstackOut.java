package jvm.jstack;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 类 名 称：JstackOut.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年07月23日
 */
public class JstackOut {

    public static void main(String[] args) {
        singleThread();
        threadPool();
        while (true) ;
    }

    private static void singleThread() {
        Thread singleThread = new Thread(() -> {
            while (true) ;
        });
        singleThread.setName("singleThread");
        singleThread.start();
    }

    private static void threadPool() {
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> {
            while (true) ;
        });
    }

}
