package com.gcl.blog.model;

import java.util.Date;

/**
 * 博客信息实体类
 */
public class Blog {
    //成员变量
    private String id;//博客唯一标识
    private String title; //标题
    private String content; //博客内容
    private String author_email; //博客作者的email
    private Date publish_time; //发布时间
    private String label; //标签
    private String classify; //分类
    private int comment; //评论数
    private int like; //点赞数
    private int collect; //收藏数
    private int browse; //浏览数
    private int is_ok; //审核状态
    private String partContent; //部分内容
    private String nickName;//作者昵称
    private int count;//分类数量
    //构造方法
    public Blog(){}

    public Blog(String id, String title, String content, String author_email, Date publish_time, String label, String classify,int is_ok,String partContent) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_email = author_email;
        this.publish_time = publish_time;
        this.label = label;
        this.classify = classify;
        this.is_ok=is_ok;
        this.partContent=partContent;
    }
    public Blog(String id, String title, String content, String author_email, Date publish_time, String label, String classify, int comment, int like, int collect, int browse, int is_ok,String partContent) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_email = author_email;
        this.publish_time = publish_time;
        this.label = label;
        this.classify = classify;
        this.comment = comment;
        this.like = like;
        this.collect = collect;
        this.browse = browse;
        this.is_ok = is_ok;
        this.partContent=partContent;
    }

    public Blog(String id, String title, String content, String author_email, Date publish_time, String label, String classify, int comment, int like, int collect, int browse, int is_ok, String partContent,int count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_email = author_email;
        this.publish_time = publish_time;
        this.label = label;
        this.classify = classify;
        this.comment = comment;
        this.like = like;
        this.collect = collect;
        this.browse = browse;
        this.is_ok = is_ok;
        this.partContent = partContent;
        this.count = count;
    }

    public Blog(String id, String author_email, int comment, int like, int collect, int browse, int is_ok) {
        this.id = id;
        this.author_email = author_email;
        this.comment = comment;
        this.like = like;
        this.collect = collect;
        this.browse = browse;
        this.is_ok = is_ok;
    }

    public Blog(String id, String title, String content, String author_email, Date publish_time, String label, String classify, int comment, int like, int collect, int browse, int is_ok, String partContent, String nickName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_email = author_email;
        this.publish_time = publish_time;
        this.label = label;
        this.classify = classify;
        this.comment = comment;
        this.like = like;
        this.collect = collect;
        this.browse = browse;
        this.is_ok = is_ok;
        this.partContent = partContent;
        this.nickName = nickName;
    }

    //getter和setter方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getIs_ok() {
        return is_ok;
    }

    public void setIs_ok(int is_ok) {
        this.is_ok = is_ok;
    }

    public String getPartContent() {
        return partContent;
    }

    public void setPartContent(String partContent) {
        this.partContent = partContent;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
