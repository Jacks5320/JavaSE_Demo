package com.demo10.d_deadlock;

/**
 * 死锁
 * 死锁很难发现和调试，会写才会处理。
 * 下面代码就是死锁，效果为，不会出异常，也不会停止程序，一直僵持这种状态。
 * 开发中最好不要嵌套使用 synchronized 关键字，容易引发死锁问题
 *
 */
public class DeadLock {
    public static void main(String[] args) {
        // 创建对象
        Object o1 = new Object();
        Object o2 = new Object();
        // 创建线程
        MyThread1 t1 = new MyThread1(o1, o2);
        MyThread2 t2 = new MyThread2(o1, o2);
        // 启动线程
        t1.start();
        t2.start();
    }
}

// 线程类 1，先锁 a 再锁 b
class MyThread1 extends Thread {
    Object o1;
    Object o2;

    public MyThread1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void run() {
        synchronized (o1) {
            // 模拟延迟，放大问题
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2) {

            }
        }
    }
}

// 线程类 2，先锁 b 再锁 a
class MyThread2 extends Thread {
    Object o1;
    Object o2;

    public MyThread2(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void run() {
        synchronized (o2) {
            // 模拟延迟，放大问题
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1) {

            }
        }
    }
}