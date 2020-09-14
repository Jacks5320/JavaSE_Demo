package com.demo10.secure.demo1;

/**
 * 银行账户
 */
public class Account {
    // 名字
    private String name;
    // 余额
    private double money;

    public Account(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    //  取钱
    // t1 和 t2 并发执行这个方法（t1 和 t2 是两个栈，两个栈操作同一个对象）
    // 下面的每一个语句都可能被 t1 和 t2 先后执行
    public boolean takeMoney(double money){
        // 查询余额
        double before = this.getMoney();

        // 判断取款金额是否大于账户余额
        if( money > before) return false;

        // 取款操作
        double after  = before - money;
        System.out.println(Thread.currentThread().getName() + "取款操作完成！");
        // 更新余额
        this.setMoney(after);
        System.out.println(Thread.currentThread().getName() + "余额更新完成");
        return true;
    }
    /*  程序分析
        假设：账户总余额 20000 元，t1 线程取 19000 和 t2 线程取 11000 元。

        1. 线程安全的情况
        t1 执行完整个取钱方法后，t2 才进入取钱方法。
        t1 => before = 20000;
        t1 => after = 20000 - 19000 = 1000;
        t1 => this.setMoney(1000);
        此时 t2 读取到的余额就是 t1 取款更新后的余额。
        t2 => before = 1000;
        t2 => 11000 > 1000 退出程序。

        2. 线程不安全情况
        t1 执行完取款操作，没有执行更新余额操作时， t2 进入取款方法。
        t1 => before = 20000;
        t1 => after = 20000 - 19000 = 1000;
        t2 => before = 20000;
        t2 => after = 20000 - 11000 = 9000;
        只要前面出现这种情况，数据就已经被污染了，后面只是更新的先后问题
        第一种情况， t1 线程先更新，t2 线程后更新，账户最终保存的余额为 t2 线程的更新结果 9000
        t1 => this.setMoney(1000)
        t2 => this.setMoney(9000)
        第二种情况，t2 线程先更新，t1 线程后更新，账户最终保存的余额为 t1 线程的更新结果 1000
        t2 => this.setMoney(9000)
        t1 => this.setMoney(1000)

        所以你多次运行这个 demo 时，会发现打印的余额值要么是 1000 ，要么是 9000，或者线程安全情况，有一个线程操作失败。

        取款方法线程不安全导致的结果：t1 与 t2 取款总额为 30000，余额为 1000 或 19000，加起来一共 31000 或 49000
        而原始账户中只有 20000 元的存款，是不是挺爽的 -_-、。



        3. 会出现线程不安全的原因
            多线程迸发程序中，每个线程是异步进行的。
            t1 线程在执行某一行代码时，可能比 t2 先执行，也可能比 t2 后执行，多核环境中也可能同时执行。
            所以导致只存 20000 元的账户最高可以取出 40000 元（因为加了判断，比余额大就不给取，否则问题就更夸张了）
     */
}
