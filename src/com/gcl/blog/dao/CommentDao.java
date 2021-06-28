package com.gcl.blog.dao;

import com.gcl.blog.model.Comment;

import java.util.List;

public interface CommentDao {
    //将评论信息插入数据库
    public int insertActive(Comment comment);
    //根据主键删除评论
    public int deleteByPrimaryKey(String commentId);
    //查找某篇文章的所有评论
    public List<Comment> selectList(String articleId);

    public List<Comment> selectList(String parentId,String articleId);
    //查询评论数
    public int countComment(String titleId);
    //统计作者所有文章的总评论数
}
