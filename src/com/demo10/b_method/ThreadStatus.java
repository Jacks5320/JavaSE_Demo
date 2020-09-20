package com.demo10.b_method;

/**
 * 线程阻塞方法
 * <p>
 * 线程休眠：public static native void sleep(long millis)
 * 作用：让当前线程进入休眠状态，进入阻塞状态，放弃占有的 CPU 时间片，让给其他线程使用。
 * 参数：毫秒
 * 注意：出现在哪个线程中，就让哪个线程休眠。与线程对象无关。就算使用某个线程的对象调用sleep()方法，最终也会转换成 Thread.sleep()，因为这是静态方法，与对象无关。
 * <p>
 * 中断线程：public void interrupt()
 * 这种方式依赖异常处理机制，会抛出一个异常，中断 try...catch 语块。
 * <p>
 * 强行终止：public final void stop()，已弃用
 * 这种方式会强行终止线程，缺点：容易丢失数据，直接将线程杀死，线程没有保存的数据会丢失。
 */
public class ThreadStatus {
    public static void main(String[] args) {
        /*
        ThreadStatusMethods t = new ThreadStatusMethods();
        // 中断线程休眠（依靠异常处理机制）
        t.ThreadInterrupt();
        // 强制终止线程，已弃用
        t.ThreadStop();
        */
        // 合理终止线程
        MyRunnable2 r = new MyRunnable2();
        Thread t2 = new Thread(r);
        t2.start();
        // 3 秒后终止线程
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "==>" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        r.flag = false;
    }
}

//  线程类1
class MyRunnable implements Runnable {

    //  子类重写的方法不能抛出比父类更高级的异常
    //  所以这里只能 try...catch
    @Override
    public void run() {
        try {
            //  休眠 1 天
            Thread.sleep(1000 * 60 * 60 * 24);
        } catch (InterruptedException e) {
            e.printStackTrace();  // 可以注释掉，不打印异常信息
        }
        // 休眠 1 年后才会执行下面这条语句
        System.out.println(Thread.currentThread().getName() + "===> end");
    }
}

//  合理终止线程类
class MyRunnable2 implements Runnable {

    //  设置 flag ，标识是否终止
    boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            if (flag) {
                System.out.println(Thread.currentThread().getName() + "==>" + i);
                i++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("保存剩余数据！");
                return;
            }
        }
    }
}

//  线程方法
class ThreadStatusMethods {

    Thread t;

    // 构造，用于 main 方法调用测试方法
    ThreadStatusMethods() {
        t = new Thread(new MyRunnable());
        t.setName("t");
        t.start();
    }

    //  线程休眠方法演示，仅在内部使用
    private static void ThreadSleep() {
        // 休眠5秒
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "==>" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //  线程中断方法演示：interrupt
    public void ThreadInterrupt() {
        // 休眠 5 秒后中断 t 线程
        ThreadSleep();
        // 中断线程，抛出异常，结束 try...catch 语块
        t.interrupt();
    }

    // 强制终止线程方法演示：stop
    public void ThreadStop() {
        // 休眠 5 秒后杀死 t 线程
        ThreadSleep();
        //  弃用方法，强制杀死线程，有丢失数据的风险。
        t.stop();
    }
}