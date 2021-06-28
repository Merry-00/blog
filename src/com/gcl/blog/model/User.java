package com.gcl.blog.model;

/**
 * 用户信息
 */
public class User {
    //成员变量
    private String email;//email
    private String nickName; //昵称
    private String sex;
    private String img;//头像
    private int fans;//粉丝
    private int focus;//关注数
    private int commentNum; //评论数
    private int likeNum; //喜欢数
    private int collectNum;//收藏数
    private int browseNum;//浏览数
    //构造方法
    public User(){}

    public User(String email, String nickName, String sex, String img, int fans, int focus) {
        this.email = email;
        this.nickName = nickName;
        this.sex = sex;
        this.img = img;
        this.fans = fans;
        this.focus = focus;
    }
    public User(String email, String nickName, String sex, String img, int fans, int focus, int commentNum, int likeNum, int collectNum, int browseNum) {
        this.email = email;
        this.nickName = nickName;
        this.sex = sex;
        this.img = img;
        this.fans = fans;
        this.focus = focus;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.collectNum = collectNum;
        this.browseNum = browseNum;
    }
    //getter和setter方法

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }
    //toString()

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", img='" + img + '\'' +
                ", fans=" + fans +
                ", focus=" + focus +
                ", commentNum=" + commentNum +
                ", likeNum=" + likeNum +
                ", collectNum=" + collectNum +
                ", browseNum=" + browseNum +
                '}';
    }
}
