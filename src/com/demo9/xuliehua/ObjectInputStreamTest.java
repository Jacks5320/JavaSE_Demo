package com.demo9.xuliehua;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 反序列化
 */
public class ObjectInputStreamTest {

    public static void main(String[] args) throws Exception {
        //  反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student"));
        Object obj = ois.readObject();
        System.out.println(obj);
        ois.close();
    }

}
