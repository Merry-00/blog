package com.gcl.blog.service;

import com.gcl.blog.model.Comment;

import java.util.List;

public interface CommentService {

    boolean add(Comment comment);

    List<Comment> list(String articleId);

    List<Comment> list(String parentId,String articleId);

    boolean remove(String commentId);
}
