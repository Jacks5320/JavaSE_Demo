package com.demo10.g_waitandnotify;

/**
 * wait 和 notify 练习：一个线程打印基数，一个线程打印偶数
 */
public class WorkDemo {
    public static void main(String[] args) {
        int[] i = new int[1];
        i[0] = 1;
        Thread t1 = new T1(i);
        Thread t2 = new T2(i);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

class T1 extends Thread {

    int[] i;

    public T1(int[] i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (i) {
                if (i[0] % 2 == 0) {
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "-->" + i[0]++);
                    // 唤醒
                    i.notify();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class T2 extends Thread {
    int[] i;

    public T2(int[] i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (i) {
                if (i[0] % 2 != 0) {
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "-->" + i[0]++);
                    // 唤醒
                    i.notify();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
