package com.demo10.g_waitandnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 wait 和 notify 实现生产者和消费者模式
 * 生产者和消费者模式：
 * - 生产者线程负责生产
 * - 消费者线程负责消费
 * - 生产和消费需达到均衡，生产满了必须等待消费，消费完了必须等待生产
 * <p>
 * - wait 和 notify 是 Object 对象的方法，不是线程对象的方法
 * - wait 和 notify 方法建立在线程同步的基础之上。
 * - o.wait() 使当前线程 t 等待，并且释放之前 t 线程占用 o 对象的锁。直到另一个线程调用 notify 方法。
 * - o.notify() 唤醒正在此对象的监视器上等待的单个线程。只是通知，不会释放 o 对象上之前占用的锁
 */
/*
需求：
- 使用 list 集合模拟仓库，且最多只能存放一个元素。
- 如果仓库满了，就等待消费者消费。
- 如果仓库空了，就等待生产者生产。
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 创建共享仓库
        List list = new ArrayList();
        // 生产者线程
        Thread t1 = new Thread(new Producer(list));
        t1.setName("生产者");
        // 消费者线程
        Thread t2 = new Thread(new Consumer(list));
        t2.setName("消费者");
        // 启动线程
        t1.start();
        t2.start();
    }
}

// 生产线程
class Producer implements Runnable {
    // 仓库
    private List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        // 一直生产
        while (true) {
            // 给 list 对象加锁
            synchronized (list) {
                // 集合有元素，进入等待
                if (list.size() > 0) {
                    // 等待状态，并且释放锁，让其他线程可以操作。
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 仓库空，可以生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "--->" + obj);
                // 唤醒消费者消费
                list.notify();
            }
        }
    }
}

// 消费者线程
class Consumer implements Runnable {

    // 仓库
    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        // 一直消费
        while (true) {
            synchronized (list) {
                // 仓库空了，进入等待状态
                if (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 仓库不为空，可以消费
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + "--->" + obj);
                // 唤醒生产者生产
                list.notify();
            }
        }
    }
}