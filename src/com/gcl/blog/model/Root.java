package com.gcl.blog.model;

/**
 * 权限管理实体类
 */
public class Root {
    //成员变量
    private int rootId; //权限ID
    private int pathId; //路径id
    private String path; //可访问路径
    //构造方法
    public Root(){}

    public Root(int rootId, String path) {
        this.rootId = rootId;
        this.path = path;
    }
    //getter和setter方法

    public int getRootId() {
        return rootId;
    }

    public int getPathId() {
        return pathId;
    }

    public String getPath() {
        return path;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
