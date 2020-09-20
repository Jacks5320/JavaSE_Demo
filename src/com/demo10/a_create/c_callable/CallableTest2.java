package com.demo10.a_create.c_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现 callable 接口创建线程类，JDK8 新特性
 * 相比于前两种，这种方式可以获取线程返回值。前两种 run 方法用 void 修饰，这种使用。
 * <p>
 * java.util.concurrent.FutureTask 在 Java 并发包下(juc)，老 JDK 中没有，新特性。用于包装 Runnable 和 Callable 对象
 * <p>
 * 效率较低，在 a 线程中使用 get方法获取 b 线程的结果，会阻塞 a 线程，当 b 线程执行完并返回结果后，a 线程才能继续往下执行。
 * 好处是可以获取线程处理结果。
 */
public class CallableTest2 {
    public static void main(String[] args) throws Exception {
        // 1. 创建 Callable 实现对象
        MyCallable callable = new MyCallable();
        // 2. 创建未来任务类对象，接受 Callable 实现对象
        FutureTask<Integer> task = new FutureTask<>(callable);
        // 3. 创建线程对象，使 FutureTask 具备多线程能力
        Thread t = new Thread(task);
        // 4. 启动线程
        t.start();
        // 5. 在 main 线程中获取 t 线程的执行结果
        // get() 会让当前线程（main）阻塞，只有另一个线程执行完毕才会产生结果，所以 main 方法会一直等。
        Object obj = task.get();
        System.out.println(obj);
    }
}

// 实现 Callable<Integer> 接口，重写 call 方法
class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("call method begin...");
        // 3 秒后获取结果
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int a = 100;
        int b = 200;
        System.out.println("call method end...");
        return a + b;  // 会自动装箱 int -> Integer
    }
}
