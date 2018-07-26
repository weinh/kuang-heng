package jvm.jstack;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类 名 称：JstackOut.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年07月23日
 */
public class JstackOut {

    Object a = new Object();
    Object b = new Object();

    public static void main(String[] args) {
//        singleThread();
//        threadPool();
        new JstackOut().deadlock();
        while (true) ;
    }

    private static void singleThread() {
        Thread singleThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        singleThread.setName("singleThread");
        singleThread.start();
    }

    private static void threadPool() {
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> {
            Thread.currentThread().setName("thread pool 1");
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(() -> Thread.currentThread().setName("thread pool 2"));
    }

    private void deadlock() {
        for (int i = 0; i < 10; i++) {

            Thread threadA = new Thread(() -> {
                Thread.currentThread().setName("thread a");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    synchronized (b) {
                    }
                }

            });

            Thread threadB = new Thread(() -> {
                Thread.currentThread().setName("thread b");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    synchronized (a) {
                    }
                }
            });
            threadA.start();
            threadB.start();
        }
    }
}
