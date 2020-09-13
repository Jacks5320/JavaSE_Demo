package com.demo10.method;
/*
    获取当前线程对象
    获取线程对象的名字
    修改线程对象的名字
 */
public class ThreadMethod {
    public static void main(String[] args) {
        //  创建线程对象
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        //  获取线程对象名称 getName()
        //  默认值为 Thread - n，n 为第 n 个线程，从 0 开始技术。
        System.out.println(t1.getName());
        System.out.println(t2.getName());
        //  设置线程名字 setName()
        t1.setName("线程一");
        t2.setName("线程二");
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        t1.start();
        t2.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++){
            System.out.println("<----------- run() ---------->" + this.getName());
        }
    }
}