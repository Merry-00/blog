package com.gcl.blog.service;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.EchartsBean;
import com.gcl.blog.model.PageBean;

import java.util.ArrayList;
import java.util.List;

public interface BlogService {
    //博客中心
    //3.浏览博客功能----查找所有的博客
    ArrayList<Blog> selectAllBlog(int isOk);
    //3.编辑发布博客功能
    boolean addBlog(Blog blog);
    //4.删除博客功能
    boolean delete(String id);
    Blog getBlogById(String blogId);
    //根据email查询所有的博客
    List<Blog> queryMyBlog(String email);
    //修改博客
    boolean updateBlog(Blog blog);
    //修改博客浏览数
    boolean updateNum(Blog blog);
    //查看评论数
    int selectCommentNumber(String articleId);
    //将评论数插入数据库中
    int updateComment(String articleId);
    //文章模糊查询
    ArrayList<Blog> selectKey(String info);
    //5.博客分类功能
    ArrayList<Blog> queryClassifyBlog(String email);
    //审核博客状态
    boolean verifyBlog(String id,int operate);
    //根据echarts分类博客
    ArrayList<EchartsBean> queryClassifyCount(String email);
    //分页查询博客信息，参数为当前页和页面大小

    PageBean<Blog> queryBlogByPage(int currentPage,int pageSize);
    //6.博客按要求查找
    //7.个人博客分类显示
}
