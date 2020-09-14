package com.demo10.secure.demo1;

/**
 * 测试账户安全
 */
public class Test {
    public static void main(String[] args) {

        // 初始化账户
        Account account = new Account("aaa", 20000.0);
        // 线程对象
        Thread t1 = new AccountThread(account,19000);
        Thread t2 = new AccountThread(account,11000);
        // 线程名
        t1.setName("t1");
        t2.setName("t2");
        // 启动线程
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("账户余额：" + account.getMoney());
    }
}
