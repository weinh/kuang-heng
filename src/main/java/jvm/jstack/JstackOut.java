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

    Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        threadPool();
        new JstackOut().deadlock();
        new JstackOut().waitingLock1();
        new JstackOut().waitingLock2();
        new JstackOut().waiting();
        while (true) ;
    }

    private static void threadPool() {
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> Thread.currentThread().setName("thread pool 1"));
        executor.execute(() -> {
        });
    }

    private void waiting() throws InterruptedException {
        new Thread(() -> {
            Thread.currentThread().setName("sleep thread");
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("wait thread");
            synchronized (a) {
                try {
                    a.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("wait time thread");
            synchronized (a) {
                try {
                    a.wait(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.currentThread().join(10 * 1000);
        Thread.currentThread().join();
    }

    private void waitingLock1() throws InterruptedException {
        new Thread(() -> {
            Thread.currentThread().setName("lock thread1");
            lock.lock();
            while (true) ;
        }).start();

        Thread.sleep(500);

        new Thread(() -> {
            Thread.currentThread().setName("lock thread2");
            lock.lock();
        }).start();
    }

    private void waitingLock2() throws InterruptedException {
        new Thread(() -> {
            Thread.currentThread().setName("synchronized thread1");
            synchronized (a) {
                while (true) ;
            }
        }).start();

        Thread.sleep(500);

        new Thread(() -> {
            Thread.currentThread().setName("synchronized thread2");
            synchronized (a) {
            }
        }).start();
    }

    private void deadlock() {
        for (int i = 0; i < 1; i++) {

            Thread threadA = new Thread(() -> {
                Thread.currentThread().setName("thread a");
                synchronized (a) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });

            Thread threadB = new Thread(() -> {
                Thread.currentThread().setName("thread b");
                synchronized (b) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threadA.start();
            threadB.start();
        }
    }
}
