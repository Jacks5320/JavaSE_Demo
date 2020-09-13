package com.demo1.abstractDemo;

/**
 * 测试类，包含主函数 main()，程序的入口
 */
public class AbstractTest {
    //  main 函数固定写法
    //  public 表示公共的，都可以访问
    //  static 表示静态的，程序启动就会被加载
    //  void 表示该方法没有返回值
    //  string[] args 表示调用该方法需要传入的参数
    public static void main(String[] args) {
        //  多态写法，以父类为声明，使用子类实例化对象。
        //  优点：维护方便、扩展性高
        //  缺点：不能使用子类特有的成员
        Animal animal = new Dog("旺财");
        //  查看实例化对象
        System.out.println("创建的对象是谁的：" + animal.getClass());
        //  调用方法，不能调用子类特有的方法
        //  父类实体方法
        animal.eat();
        //  父类抽象方法
        animal.run();
    }
}
