package com.demo2;

/**
 * 基本类型与包装类型，每种基本类型都有与其对于的类，这些类称为包装器(wrapper)(对象)。
 * （String 不是基本类型，底层使用char数组实现）
 * 将基本类型转换包装类型（对象）称为装箱，如 int 转变为 Integer
 * 字符串与数字的转换就需要使用包装类。
 * 除此之外，还可以调用方法。
 *
 * byte => Byte
 * short => Short
 * int => Integer
 * long => Long
 * char => Character
 * float => Float
 * double => Double
 * boolean => Boolean
 *
 * 下面以 int 与 Integer 作为示例
 */

public class WrapperObj {

    public static void main(String[] args) {
        /*
        JDK1.5 以后可以直接赋值
        Integer a = 20;  <==> Integer a = new Integer(20)；//  装箱
        int b = a;  <==> int b = a.intValue();  //  拆箱
         */
        //  装箱，自动，基本类型 -> 引用类型
        Integer a = 20;
        //  拆箱，调用方法，引用类型 -> 基本类型
        int b = a.intValue();
        //  比较的是值
        System.out.println(a == b);  // true
        //  String 转 int
        String c = "20";
        int d = Integer.parseInt(c);  // parseInt 有多个重载，根据需求选择
        System.out.println(a == d);  // true
        //  int 转 String
        String e = Integer.toString(d);
        System.out.println(c == e);  //  false，不是一个引用，两个不同对象
        System.out.println(c.equals(e));  // true，比较字符串的值使用 equals 方法
    }
}
