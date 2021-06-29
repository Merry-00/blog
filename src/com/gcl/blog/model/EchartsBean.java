package com.gcl.blog.model;

public class EchartsBean {
    //成员变量
    private String name;
    private int value;
//构造方法
    //空构造方法
    public EchartsBean(){}
    public EchartsBean(String name, int value) {
        this.name = name;
        this.value = value;
    }
    //setter和setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    //toString()
    @Override
    public String toString() {
        return "EchartsBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
