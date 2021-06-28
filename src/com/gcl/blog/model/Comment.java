package com.gcl.blog.model;

import java.util.Date;

public class Comment {
    //成员变量
    private String commentId; //评论id
    private String email; //评论人的email
    private String parentId; //
    private String titleId; //文章id
    private String content; //评论内容
    private Date commentTime; //评论时间
    private String nickName;//评论人的昵称
    //构造方法
    public Comment(){}

    public Comment(String commentId, String email, String parentId, String titleId, String content, Date commentTime) {
        this.commentId = commentId;
        this.email = email;
        this.parentId = parentId;
        this.titleId = titleId;
        this.content = content;
        this.commentTime = commentTime;
    }

    public Comment(String commentId, String email, String parentId, String titleId, String content, Date commentTime, String nickName) {
        this.commentId = commentId;
        this.email = email;
        this.parentId = parentId;
        this.titleId = titleId;
        this.content = content;
        this.commentTime = commentTime;
        this.nickName = nickName;
    }
    //getter和setter方法

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
