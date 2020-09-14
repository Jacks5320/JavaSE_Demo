package com.demo10.method;

/**
 * 线程调度模型
 * <p>
 * 设置线程优先级：public final void setPriority(int newPriority)
 * 最高优先级为 10、默认优先级为 5、最低优先级为 1，优先级高的线程，抢夺到 CPU 时间片更多的 “ 概率 ” 相对较大，但不是绝对的。
 * 一个优先级为 10 的 a 线程和一个优先级为 1 的 b 线程比较，a 能抢到多时间片的概率比 b 更大。
 * 例如：假设时间片长度 10，a 一次抢到 7 ，b 一次抢到 3，但也不乏 a 只抢到 3，b 抢到 7 的情况，但 a 抢到时间片多的概率相对更高。
 * <p>
 * <p>
 * 获取线程优先级：public final int getPriority()
 * <p>
 * <p>
 * 线程让位方法：public static native void yield()
 * 暂停当前正在执行的线程对象，并执行其他线程。
 * 注意：
 * yield() 方法不是阻塞方法。让当线程让位，让给其他线程使用。
 * yield() 方法的执行会让当前线程从 "运行状态" 回到 "就绪状态"，此时依然有机会抢到时间片。
 * <p>
 * 合并线程：public final void join() throws InterruptedException
 */
public class ThreadScheduling {
    public static void main(String[] args) {
        ThreadSchedulingMethods t = new ThreadSchedulingMethods();
        // 1. 打印最高、一般、最低优先级
        //t.threadPriority();
        // 2. 获取主线程与子线程的默认优先级
        //t.getDefaultPriority();
        // 3. 测试不同优先级的线程抢占情况
        //t.difPriority();
        // 4. 让线程 yield()
        //t.ThreadYield();
        // 5. 合并线程
        t.ThreadMerge();
    }
}

// 线程类
class MyRunnable3 implements Runnable {

    @Override
    public void run() {
        // 分支线程循环
        for (int i = 1; i <= 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "===========>" + i);
        }
    }
}

//  线程调度方法演示
class ThreadSchedulingMethods {
    public void threadPriority() {
        System.out.println("最高优先级==>" + Thread.MAX_PRIORITY);  // 10
        System.out.println("一般优先级==>" + Thread.NORM_PRIORITY); // 5
        System.out.println("最低优先级==>" + Thread.MIN_PRIORITY);  // 1
    }

    // 默认优先级
    public void getDefaultPriority() {
        // 获取主线程的默认优先级
        Thread m = Thread.currentThread();
        System.out.println(m.getName() + "的优先级为==>" + m.getPriority());  // 5
        // 获取分支线程默认优先级
        Thread t = new Thread(new MyRunnable3());
        t.start();
        System.out.println(t.getName() + "的优先级为==>" + t.getPriority());  // 5
    }

    // 不同优先级线程的抢占情况
    public void difPriority() {
        // 设置分支线程的优先级为 10
        Thread t = new Thread(new MyRunnable3());
        t.setName("other");
        t.setPriority(10);
        t.start();
        // 设置主线程优先级为 1
        Thread.currentThread().setPriority(1);
        // 主线程循环
        for (int i = 1; i <= 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "==>" + i);
        }
    }

    // 让线程
    public void ThreadYield() {
        Thread t = new Thread(new MyRunnable3());
        t.start();
        for (int i = 1; i <= 10000; i++) {
            //  每打印 100 次就让一次，主线程就让一次分支线程。
            if (i % 5 == 0) {
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "==>" + i);
        }
    }

    // 合并线程
    public void ThreadMerge() {
        System.out.println("<============== begin ===========>");
        Thread t = new Thread(new MyRunnable3());
        t.setName("t");
        t.start();
        try {
            // 合并线程，将 t 线程合并到主线成中，当 t 线程走完后才会继续走主线程。
            // 阻塞当前线程 b ，执行调用 join 方法的线程 a，b 线程会等待 a 线程执行完后才会继续往下执行。
            // 也叫插队
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("<============== end ===========>");
    }
}