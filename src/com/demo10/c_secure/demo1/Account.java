package com.demo10.c_secure.demo1;

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

    //  取钱，线程不安全
    public boolean takeMoney(double money) {
        // 查询余额
        double before = this.getMoney();

        // 判断取款金额是否大于账户余额
        if (money > before) return false;

        // 取款操作
        double after = before - money;
        System.out.println(Thread.currentThread().getName() + "取款操作完成！");

        // 模拟网络延迟
        //try {
        //    Thread.sleep(200);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        // 更新余额
        this.setMoney(after);
        System.out.println(Thread.currentThread().getName() + "余额更新完成");
        return true;
    }

    //  取钱，线程安全
    public boolean takeMoneySafe(double money) {
        /*  线程同步机制
            synchronized(共享对象){
                同步代码块
            }
            用 synchronized 关键字包裹的代码是同步代码块，只能一个线程进入，相当于给代码块加锁。
            共享对象必须是需要排队的多线程的共享对象，这样才能实现线程排队。就如同一个门只有一把钥匙。
            如果一个门有多把钥匙，就不能实现同一时间只有一个人进入。

            ()中写什么？
                假设 t1、t2、t3、t4、t5 5个线程并发，只希望 t1 t2 t3 排队，t4 t5 不排队。
                必须在 () 中写 t1、t2、t3 共享的对象。这个对象对于 t4、t5 来说不是共享的对象。
                这里的共享对象为 账户对象，可以用 this 来表示。
            执行原理：
                t1 和 t2 线程并发，t1 与 t2 都有可能先进入同步代码块（遇到 synchronized 关键字）
                当 t1 线程进入同步代码块后，会自动去找共享对象的对象锁并占用这把锁。
                后面的线程 t2 遇到 synchronized 关键字后，也会去找共享对象的对象锁，并占用，如果已经被占用，就会去排队。
                在程序执行过程中，t1 一直都会占有这把锁，只有同步代码块执行结束才会归还这把锁。
                此时排队的 t2 线程就会占用这把锁，执行同步代码块。

            所以选择共享对象是很重要的。
         */
        synchronized (this) {  // 能锁住，只有一个 Account 对象，且是共享的，共享该对象的线程需要排队，如果不是同一个 Account 对象，则不需要排队
        //synchronized (name) {  // 能锁住，只有一个 String 对象，且是共享的
        //synchronized (new Object()) {  // 不能锁住，多个 Object 对象，这个是局部变量，不被共享。
        //synchronized ("abc"){  // 能锁住，"abc" 在常量池中，被所有线程共享，所以所有线程执行这里时都会排队。
            double before = this.getMoney();
            if (money > before) return false;
            double after = before - money;
            System.out.println(Thread.currentThread().getName() + "取款操作完成！");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setMoney(after);
            System.out.println(Thread.currentThread().getName() + "余额更新完成");
            return true;
        }
    }

    // 用 synchronized 修饰方法，锁的是 this，不灵活
    // 修饰方法时，表示整个方法体都需要同步，可能会扩大锁范围，降低效率，根据实际情况选择加锁方式。
    // 当共享方法的整个方法体都需要加锁，且共享对象就为 this，则建议采用这种方式加锁，代码更加简洁。
    public synchronized boolean takeMoneySafe2(double money){

        double before = this.getMoney();
        if (money > before) return false;
        double after = before - money;
        System.out.println(Thread.currentThread().getName() + "取款操作完成！");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setMoney(after);
        System.out.println(Thread.currentThread().getName() + "余额更新完成");
        return true;
    }
}
/*  线程不安全程序分析
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