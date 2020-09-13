package com.demo1.abstractDemo;

/**
 * 抽象类：
 *      使用 abstract 关键字修饰的类
 *      不能被实例化，其他功能与普通类一致。
 *      必须被子类继承才能实例化，所以不能使用 final 修饰。
 *      类中可以有成员变量、成员常量(final 修饰)
 *      可以有构造方法，且能够被重载
 *
 * abstract 关键字：
 *      修饰的类是抽象类，必须被继承才能实例化。
 *      修饰的方法是抽象方法，该方法不能有实体，且必须被重写才能使用。
 *      有抽象方法的类一定是抽象类(或是接口)，抽象类中可以没有抽象方法。
 */
public abstract class Animal {
    private String name;

    //  无参构造
    public Animal() {
    }

    //  全参构造，方法重载：同名方法且参数列表不一样，与修饰符无关，发送在同一个类中。
    public Animal(String name) {
        this.name = name;
    }
    //  普通方法
    public void eat() {
        System.out.println(this.name + "<=======进食======>");
    }

    //  抽象方法，没有实体
    public abstract void run();
}
