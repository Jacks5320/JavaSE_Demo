package com.demo1.interfaceDemo;

public class InterfaceTest {
    public static void main(String[] args) {
        //  多态写法
        Sport s = new SportImpl();
        //  调用抽象方法
        s.run();
        //  调用静态方法
        Sport.jump();
        //  调用默认方法
        s.swimming();
    }
}
