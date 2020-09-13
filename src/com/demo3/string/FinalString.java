package com.demo3.string;

/**
 * String
 * 不是基本类型，而是引用类型。
 * 底层使用 char[] 实现，但这个 char[] 被 final 修饰，故不能修改，又称不可变字符序列。
 *
 *
 *
 * 验证 String="abc" 和 String s = new String("abc");
 * String s1 = "abc" 创建一个对象，存入常量池中
 * String s2 = new String("abc");  创建了 2 个对象，一个放入方法区的常量池，一个会放入堆区。
 */
public class FinalString {
    public static void main(String[] args) {
        /*
            创建了 1 个 String 对象， "abc"
         */
        String s1 = "abc";

        /*
            创建了 3 个 String 对象， "abc"、"def"、"abcdef"
            因为这里提前创建了 s = "abc" 所以只会新建2个对象 "def" 和 "abcdef"
            由此可知，每个用 "" 定义的字符串都会创建一个对象，且保存在方法区的字符串常量池中
         */
        String s2 = "abc" + "def";

        /*
            使用 new 关键字创建字符串对象，会存储在堆内存中。
            "abc" 本身为一个 String 对象，存入了常量池
            new 关键字会再复制一个对象，存入堆区，所以会创建 2 个对象
            这里 "abc" 在前面定义了，所以只能新创建一个对象，且存入堆内存中。
         */
        String s3 = new String("abc");
        //  只要使用 new 关键字就会新建一个对象存入 堆内存。
        String s4 = new String("abc");


        System.out.println(s1 == s2);//  true 同一个引用，一个对象，都直接指向常量池
        System.out.println(s1 == s3);//  false 不同引用，不同对象，一个直接指向常量池，一个间接(堆区)指向常量池
        System.out.println(s3 == s4);//  false 不同引用，不同对象，都间接指向常量池
        //  基于以上原因，比较字符串对象时，应调用 equals() 方法，String 类中重写了这个方法，可以直接比较值。
        System.out.println(s3.equals(s4));
        //  与常量比较时，建议用字符串常量调用 equals() 方法可以避免空指针异常
        System.out.println("abc".equals(s3));
    }
}
