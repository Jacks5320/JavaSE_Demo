package com.demo1.abstractDemo;

/**
 * 继承：使用 extends 关键字，只能继承一个父类
 */
public class Dog extends Animal{
    private String name;
    //  无参构造
    public Dog() {
    }
    //  全参构造，继承父类的构造器(构造方法)
    public Dog(String name) {
        super(name);
        this.name = name;
    }
    //  重写父类的抽象方法
    @Override
    public void run() {
        System.out.println("狗子跑起来了。。。" + this.name);
    }
    //  子类特有的方法
    public void work(){
        System.out.println("狗子的工作是看家。。。");
    }
}
