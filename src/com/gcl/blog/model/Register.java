package com.gcl.blog.model;

/**
 * 注册用户实体类
 */
public class Register {
    //成员变量
    private String email; //QQ邮箱
    private String password; //密码
    private int root_id; //权限id
    //构造方法
    public Register(){}

    public Register(String email, String password, int root_id) {
        this.email = email;
        this.password = password;
        this.root_id = root_id;
    }
    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }
    //getter和setter方法
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoot_id() {
        return root_id;
    }

    public void setRoot_id(int root_id) {
        this.root_id = root_id;
    }
}
