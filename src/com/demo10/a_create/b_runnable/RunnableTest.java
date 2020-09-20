package com.demo10.a_create.b_runnable;

/**
 * 第二种定义多线程方法：
 *          实现 java.lang.Runnable 接口
 *          Runnable 接口中只有一个 run() 方法，要求必须重写。
 *
 *  这种方式实现多线程，增加了程序的扩展性，因为线程类还可以继承其他类。
 *
 *  所以这种方式相比于直接继承 Thread 类，更常用，更灵活。
 */
public class RunnableTest {
    public static void main(String[] args) {
        //  创建 Runnable 实现类
        MyRunnable r = new MyRunnable();
        //  创建线程
        Thread t = new Thread(r);
        //  启动线程
        t.start();

        //  继续执行主线程
        for (int i = 0; i < 1000; i++){
            System.out.println("<*************main*************>" + i);
        }
        // 其他写法
        //  匿名内部类
        /*
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++){
                    System.out.println("<----------Runnable2---------->" + i);
                }
            }
        });
        t2.start();
         */

    }
}

//  这不是一个线程类，是一个可运行的类，还不是线程。
//  需要将 Runnable 实现类的对象作为参数传入 Thread 类，这样才能作为线程
class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            System.out.println("<-----------myRunnable----------->" + i);
        }
    }
}