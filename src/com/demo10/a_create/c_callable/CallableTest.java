package com.demo10.a_create.c_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现 callable 接口创建线程类，JDK8 新特性
 * 相比于前两种，这种方式可以获取线程返回值。前两种 run 方法用 void 修饰，这种使用。
 * <p>
 * java.util.concurrent.FutureTask 在 Java 并发包下(juc)，老 JDK 中没有，新特性
 *
 * 效率较低，在 a 线程中使用 get方法获取 b 线程的结果，会阻塞 a 线程，当 b 线程执行完并返回结果后，a 线程才能继续往下执行。
 * 好处是可以获取线程处理结果。
 */
public class CallableTest {
    public static void main(String[] args) throws Exception {
        // 1. 创建未来任务类对象，可以接收 Runnable 和 Callable
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            // call 方式等价于 run 方法，call 方法有返回值
            @Override
            public Integer call() throws Exception {
                System.out.println("call method begin...");
                Thread.sleep(1000 * 3);
                int a = 100;
                int b = 200;
                System.out.println("call method end...");
                return a + b;  // 会自动装箱 int -> Integer
            }
        });
        // 2. 创建线程对象
        Thread t = new Thread(task);
        // 3. 启动线程
        t.start();

        // 在 main 线程中获取 t 线程的执行结果
        // get() 会让当前线程（main）阻塞，只有另一个线程执行完毕才会产生结果，所以 main 方法会一直等。
        Object obj = task.get();
        System.out.println(obj);
    }
}
