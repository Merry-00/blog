package com.gcl.blog.model;

import java.util.Date;

/**
 * 图片实体类
 */
public class Photo {
    //成员变量
    private String id;//图片唯一标识，使用uuid
    private String path;//存储图片路径
    private int size;//图片大小
    private Date date;//图片获取的时间
    //构造方法
    public Photo(){}
    public Photo(String id, String path, int size, Date date) {
        this.id = id;
        this.path = path;
        this.size = size;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
