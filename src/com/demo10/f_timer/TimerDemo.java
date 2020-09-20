package com.demo10.f_timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class TimerDemo {
    public static void main(String[] args) throws Exception {
        // 创建定时器对象
        Timer timer = new Timer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-09-19 12:11:00");
        // schedule(定时任务，第一次执行时间，间隔多久执行一次，单位秒)
        timer.schedule(new MyTimerTask(), date, 1000 * 2);
    }
}

// 编写定时任务类，可以写子类，也可以直接使用匿名内部类
class MyTimerTask extends TimerTask{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        Date date = new Date(System.currentTimeMillis());
        // 编写需要执行的任务
        System.out.println(sdf.format(date) + "完成一次定时任务执行了");
    }
}