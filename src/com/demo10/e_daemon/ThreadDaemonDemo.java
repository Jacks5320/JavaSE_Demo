package com.demo10.e_daemon;

/**
 * 守护线程
 */
public class ThreadDaemonDemo {
    public static void main(String[] args) {
        Thread t = new ThreadDaemon();
        t.setName("守护线程");
        // 设置守护线程，public final void setDaemon(boolean on)
        // 当所有的用户线程结束后，这个线程也会结束
        t.setDaemon(true);
        // 启动线程
        t.start();

        // 主线程，用户线程
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " ==> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadDaemon extends Thread{

    public void run() {
        int i = 0;
        while (true){
            System.out.println(Thread.currentThread().getName() + " ==> " + (++i));
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
