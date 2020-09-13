package com.demo3.string;

/**
 * 每次使用 "+" 拼接字符串时，都会至少创建一个对象放入方法区字符串常量池中，且不会被垃圾回收机制回收。
 * 当过于频繁拼接时，会给方法区字符串常量池带来巨大的压力，且会占用大量内存，造成空间浪费。
 * 基于以上原因，出现了 StringBuffer(JDK1.0) 和 StringBuilder(JDK1.5)。
 *
 * StringBuilder 与 StringBuffer，可以动态修改字符串，故称可变字符序列
 * StringBuilder 与 StringBuffer 都继承于 AbstractStringBuilder，共用一套 API。
 *
 * StringBuffer优化
 *      最好给定合适初始化容量，减少低沉扩容次数。
 *
 */
public class StringBuilderAndBuffer {
    public static void main(String[] args) {
        /*
            StringBuffer，默认初始化容量 16 个字符，JDK8版本底层是 char[]，有的版本为 byte[]
         */
        StringBuffer s1 = new StringBuffer();
        //  追加字符串，有多个重载，可以传入非 String 类型，最终都会被转化为String 类型进行拼接。
        //  会自动扩容
        s1.append("a");
        s1.append(3.14);
        s1.append(true);
        System.out.println(s1);
        // 指定容量
        StringBuffer s2 = new StringBuffer(100);//单位字符个数

        /*
            StringBuilder，与StringBuffer 作用一致，用于拼接字符串
            StringBuffer 是线程安全的，方法用 synchronized 修饰，效率较低，但在迸发环境中是安全的。
            StringBuilder 是非线程安全的，效率更高，但在迸发环境不安全。
         */

    }
}
