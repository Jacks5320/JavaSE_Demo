package com.demo10.secure.demo1;

/**
 * 账户线程
 */
public class AccountThread extends Thread {
    // 两个线程共享一个对象
    private Account account;
    // 取款金额
    private double money;
    public AccountThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {

            // 取款操作
            boolean flag = account.takeMoney(money);
            // 反馈信息
            if (flag) {
                System.out.println(Thread.currentThread().getName() + "取款成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "取款失败");
            }

    }
}
