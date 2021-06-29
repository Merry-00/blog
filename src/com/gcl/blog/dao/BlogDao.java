package com.gcl.blog.dao;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.EchartsBean;
import com.gcl.blog.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客基本信息实体类
 */
public interface BlogDao {
    ArrayList<Blog> selectAll(int isOk);
    //添加博客
    int insertBlog(Blog blog);
    //删除博客
    int deleteBlog(String id);
    Blog selectByPrimaryKey(String blogId);
    //根据email查询所有的博客
    ArrayList<Blog> selectByEmail(String email);
    int updateBlog(Blog blog);
    int updateInfo(Blog blog);
    //查询统计评论数
    public int countComment(String titleId);
    public int insertCountComment(String titleId);
    //根据文章标题查找
    public ArrayList<Blog> queryKey(String info);
    //按类别查询博客
    public ArrayList<Blog> queryClassify(String email);
    //在echart中按类别查询博客
    public ArrayList<EchartsBean> selectClassify(String email);
    //查询博客的审核状态
    public ArrayList<Blog> selectPublish();
    //修改博客审核状态
    int updateBlogVerify(String id,int operate);
    //分页---查询总记录数
    public int getTotalRecords();
    //分页---分页查询博客列表，参数为当前页和页面大小
    public List<Blog> queryBlogByPage(int currentPage,int pageSize);

}
