package com.demo3.string;

/**
 * String 的构造器
 */
public class StringConstructor {
    public static void main(String[] args) {
        //  JDK1.5 引入，最常用
        String s1 = "hello";
        System.out.println(s1);  // hello

        //  传入 byte[]
        // public String(byte bytes[])
        byte[] bytes = {97,98,99};  // 97==>a, 98==>b,99==>c
        String s2 = new String(bytes);
        System.out.println(s2);  // abc

        // 传入 byte[] 部分字符
        // public String(char value[], int offset, int count)
        //  offset 起始索引，count 字符个数
        String s3 = new String(bytes,1,2);
        System.out.println(s3);  // el

        // 传入 char[]
        char[] chars = {'h','o','l','l','e'};
        String s4 = new String(chars);
        System.out.println(s4);

        // 传入 char[] 部分字符
        // public String(char value[], int offset, int count)
        String s5 = new String(chars,0,2);
        System.out.println(s5);

        // 通过使用指定的字符集解码指定的子数组来构造新的String。
        /*
            String(byte[] bytes, String charsetName)
         */
    }
}
