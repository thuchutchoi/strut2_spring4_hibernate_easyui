package com.howtodoinjava.dao;
import java.util.Date;
public class Student {
    private Integer id;
    private Integer age;
    private String name;
    private Date birthday;
    private String className;
    private char sex;
    
    public Student(){ }
    public Student(Integer id, Integer age, String name, Date birthday, String className, char sex) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
        this.className = className;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public char getSex() {
        return sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    
}