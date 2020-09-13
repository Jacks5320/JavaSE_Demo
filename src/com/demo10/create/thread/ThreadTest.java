package com.demo10.create.thread;

/**
 * 第一种实现多线程的方法
 *      继承 java.lang.Thread ，重写 run() 方法
 * 注意：
 *      下面的程序有先有后，有多有少，
 */
public class ThreadTest {
    //  主线程
    public static void main(String[] args) {
        //  创建线程
        MyThread myThread = new MyThread();
        //  直接调用 run() 方法
        //  这样不能实现迸发，没有开辟新的栈空间，相当于只是普通对象调用普通的方法。
        //myThread.run();


        //  启动线程，开启新的栈空间 start() 方法
        //  start() 方法的作用：启动一个分支线程，在JVM 中开辟一个新的栈空间。
        //  这段任务完成之后瞬间就结束了，只是为了开辟一个新的栈空间。
        //  启动成功的线程会自动调用 run() 方法，并且 run() 方法在分支栈的栈底部。
        //  可以将分支线程的 run() 方法理解为主线程的 main() 方法
        myThread.start();

        //  主线程中的循环
        //  方法体中的代码永远是至上而下执行，start() 方法执行完后，下面的代码才会和 run()方法中的代码迸发执行。
        for (int i = 0; i < 1000; i++){
            System.out.println("<**************main*************>" + i);
        }
    }
}

/**
 * 定义线程类，继承 java.lang.Thread ，重写 run() 方法
 */
class MyThread extends Thread{

    //  线程启动后（start()方法执行），会自动执行 run() 方法。
    //  无需手动调用，run()方法在分支线程的地位如同main()方法在主线程的地位。
    //  都作为程序的入口。
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            System.out.println("<-----------myThread----------->" + i);
        }
    }
}
