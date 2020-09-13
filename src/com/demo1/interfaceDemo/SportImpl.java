package com.demo1.interfaceDemo;

/**
 * 接口实现类（为了命名规范，可以在接口名后面加上 Impl 表示是接口实现类）
 */
public class SportImpl implements Sport{
    @Override
    public void run() {
        System.out.println("<== run() ==>" + Sport.name);
    }
    //  默认方法可以不重写
    /*
    @Override
    public void swimming() {
        System.out.println("<== swimming() ==>");
    }
     */
}
