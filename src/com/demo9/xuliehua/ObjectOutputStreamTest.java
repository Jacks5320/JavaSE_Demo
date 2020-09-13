package com.demo9.xuliehua;

import com.demo9.pojo.Course;
import com.demo9.pojo.Student;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化
 */
public class ObjectOutputStreamTest {
    //  序列化测试
    public static void main(String[] args) throws Exception {
        //  创建 Java 对象
        Student student = new Student("张三",new Course("语文"));
        //  序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student"));
        // 序列化对象
        oos.writeObject(student);
        // 刷新
        oos.flush();
        //  关闭
        oos.close();
        //  仅仅是以上操作，NotSerializableException，对象不支持序列化。
        //  需要让被序列化的类实现 Serializable 接口
        //  该接口是空接口
    }
}
