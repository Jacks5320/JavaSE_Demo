package com.demo1.interfaceDemo;

/**
 * 接口：
 *      接口技术用于描述类具有什么功能，但并不给出具体实现。
 *      对外提供一组规则、标准。
 *      不能被实例化，需要被其他类实现。使用 implement 关键字
 *      可以使用多态写法。
 *      可以多实现，一个类可以实现多个接口
 *      接口类不能被 final 修饰
 *      接口方法不能被 private 关键字。(JDK9 版本以后可以拥有私有方法 private 修饰)
 *
 * 接口成员变量
 *      接口类中不允许定义成员变量
 *      可以定义公有的静态常量（使用public static final 修饰）
 *
 * 接口成员方法
 *      JDK7及以前，只能有公有的(public修饰)或抽象的(abstract 修饰)方法；
 *      JDK8以后，新增默认方法(default 修饰)和静态方法(static修饰)；
 *      JDK9以后，新增私有方法(private 修饰)。
 *
 * 继承与实现的区别：
 *      继承：体现 is-a 关系，父类中定义共性属性。
 *      实现：体现 like-a 关系，接口中定义扩展功能。
 * 接口与抽象类的区别：
 *
 *
 *
 */
public interface Sport {
    /*
      成员常量，可以省略 public 和 static 关键字
      必须被初始化
     */
    //  成员常量
    public static String name = "Jacks";

    /*
        JDK7以前：抽象方法，可以省略public 和 abstract 关键字
     */
    //  抽象方法
    public abstract void run();

    /*
        JDK8以后：静态方法，可以省略 public 关键字
        必须拥有实体，不能被重写，程序启动时加载
        与普通类的静态方法功能一致，需要对象的引用来调用这个方法
     */
    //  静态方法
    public static void jump(){
        System.out.println("<== init ==>" + name);
    }
    /*
        JDK8以后：默认方法，使用 default 关键字修饰
        必须拥有实体，可以不重写，实例化时被加载
     */
    //  默认方法
    public default void swimming(){
        System.out.println("<== swimming ==>" + name);
    }
    //  私有方法，我的环境是 JDK1.8 所以没法演示
}
