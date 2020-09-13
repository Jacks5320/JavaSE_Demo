package com.demo3.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * String
 *
 * 不是基本数据类型，而是引用数据类型，底层使用 char[] 实现。
 *
 * 双引号括起来的字符串都直接存储在方法区的常量池当中，因为使用频繁，sun公司如此设计。
 *
 * 因其被 final 修饰，一旦定义就不能修改（如"abc" 不能变成 "def"），又称不可变字符序列。
 *
 * Java中所有字符串字面值(如："abc")，都是 String 的实例(对象)。
 *
 */
public class StringMethod {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String s = sc.next();
        //  定义字符串
        String a = "hello,world!";
        String b = "Hello,WORLD!";

        /*
            常用方法
         */
        // equals()，比较两个字符串的值，区分大小写
        System.out.println("比较字符串是否相同，区分大小写 equals()：" + a.equals(b));
        // equalsIgnoreCase()，比较两个字符串的值，不区分大小写
        System.out.println("比较字符串是否相同，不区分大小写 equalsIgnoreCase()：" + a.equalsIgnoreCase(b));
        // isEmpty()，判断是否为空
        System.out.println("判断字符串是否为空 isEmpty()：" + a.isEmpty());
        // contains()，判断是否包含指定字符串
        System.out.println("判断字符串中是否包含指定字符串，contains()：" + a.contains("hello"));  // 包含返回 true ，不包含返回false
        // length()，获取字符串长度
        System.out.println("获取字符串长度 length()：" + a.length());
        // substring(int pre,int suffix)，指定索引位置获取子字符串
        System.out.println("返回指定索引位置以及后面的全部字符 substring()：" + a.substring(0,6));  //  左闭右开
        // substring(int pre)，指定索引起点，获取子字符串
        System.out.println("返回指定索引位置全部字符 substring()：" + a.substring(5));
        // replace()，替换字符串
        System.out.println("替换字符串中的字符，replace()：" + a.replace(",","--")); //  将 , 替换为 --
        // trim()，除去首尾任意空格字符
        System.out.println("去掉首尾空格字符，trim()：" + "   a   b   ".trim());
        /*
            其他方法
         */
        // compareTo()，基于每个字符的 unicode 比较，从左至右依次比较，能分大小，后面不再继续比较。
        // 如："abc".compareTo("acb") ，当比较到第二个字符时，b < c，此时返回 -1
        System.out.println("按字典顺序比较两个字符串，compareTo()：" + "abc".compareTo("acb"));//相等返回 0，前者大返回1，后者大返回-1
        // startsWith()
        System.out.println("判断是否以给定字符串开头，区分大小写 startsWith()：" + a.startsWith("H"));
        // endsWith()
        System.out.println("判断是否以给定字符串结尾，区分大小写 endsWith()：" + a.endsWith("!"));
        // indexOf()
        System.out.println("判断字符串中是否出现过自定字符串，indexOf()：" + a.indexOf("world"));   //出现过则返回第一次出现位置的索引，未出现过返回 -1
        // charAt()
        System.out.println("返回指定索引处的字符 charAt()：" + a.charAt(1));
        // toLowerCase()
        System.out.println("大写转小写，toLowerCase()：" + "ABC".toLowerCase());  //  使用 Locale，支持自定义规则，未指定则采用默认规则
        // toUpperCase()
        System.out.println("小写转大写，toUpperCase()：" + "abc".toUpperCase());

        //  类型转换：String 转数组
        byte[] c = a.getBytes();  //  String 转 byte[]
        char[] d = a.toCharArray();  //  String 转 char[]

        Collections.reverse(Arrays.asList(d.clone()));
        //  类型转换：其他类型转换为 String
        int e = 15;
        //  有多个重载，可以将其他类型转化为 String 类型，如过是一个对象，则调用该对象的toString()方法
        //  println()方法输出任何数据时，都会先转化为字符串再进行输出。
        String f = String.valueOf(e);

        //  以指定字符拆分字符串
        String[] g = "a,b,c,d".split(",");
        for (String i : g){
            System.out.println("以 , 拆分字符串，split()：" + i);
        }

        System.out.println("更多方法查看 api 手册");
    }
}
