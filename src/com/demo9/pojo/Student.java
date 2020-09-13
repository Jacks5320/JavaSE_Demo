package com.demo9.pojo;

import java.io.Serializable;

public class Student implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;
    private Course course;

    public Student() {
    }

    public Student(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

