package com.demo10.method;
/**
    1. 获取当前线程对象，出现在哪个线程中就获取哪个线程对象
        static Thread   currentThread()
    2. 获取线程对象的名字
        public final String getName()
    3. 修改线程对象的名字
        public final synchronized void setName(String name)
 */
public class ThreadObject {
    public static void main(String[] args) {
        //  创建线程对象
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        //  获取线程对象名称 getName()
        //  默认值为 Thread - n，n 为第 n 个线程，从 0 开始技术。
        System.out.println(t1.getName());
        System.out.println(t2.getName());
        //  设置线程名字 setName()
        t1.setName("t1");
        t2.setName("t2");
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        t1.start();
        t2.start();

        for (int i = 1; i < 100; i++){
            // 出现在哪个线程中就获取哪个线程对象
            Thread m = Thread.currentThread();
            System.out.println(m.getName() + "==>" + i);
        }

    }
}

class MyThread extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++){
            // 获取当前线程对象
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + "==>" + i);
            //  super 与 this 只能出现在线程类中才能获取
            //System.out.println(super.getName() + "==>" + i);
            //System.out.println(this.getName() + "==>" + i);
        }
    }
}