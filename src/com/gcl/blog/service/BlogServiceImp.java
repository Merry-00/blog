package com.gcl.blog.service;

import com.gcl.blog.dao.BlogDaoImp;
import com.gcl.blog.dao.UserDaoImp;
import com.gcl.blog.model.Blog;
import com.gcl.blog.model.EchartsBean;
import com.gcl.blog.model.PageBean;
import com.gcl.blog.model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BlogServiceImp implements BlogService {

    /**
     * 查找所有的博客文章
     * @return
     */
    BlogDaoImp blogDaoImp=new BlogDaoImp();
    @Override
    public ArrayList<Blog> selectAllBlog(int isOk) {
        ArrayList<Blog> blogs=blogDaoImp.selectAll(isOk);
        if(blogs!=null){
            return blogs;
        }
        return null;
    }

    /**
     * 添加博客功能
     * @param blog
     * @return
     */
    @Override
    public boolean addBlog(Blog blog) {
        if(blogDaoImp.insertBlog(blog)==1){
            return true;
        }
        return false;
    }

    /**
     * 删除博客功能
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        if(blogDaoImp.deleteBlog(id)==1){
            return true;
        }
        return false;
    }

    @Override
    public Blog getBlogById(String blogId) {
        return blogDaoImp.selectByPrimaryKey(blogId);
    }

    /**
     * 查询某个用户的所有文章
     * @param email
     * @return
     */
    @Override
    public List<Blog> queryMyBlog(String email) {
        List<Blog> blogs=null;
        blogs=blogDaoImp.selectByEmail(email);
        if(blogs!=null&&blogs.size()>0){
            return blogs;
        }
       return null;
    }

    @Override
    public boolean updateBlog(Blog blog) {
        if(blogDaoImp.updateBlog(blog)==1){
            return true;
        }
        return false;
    }

    /**
     * 更新博客的浏览数
     * @param blog
     * @return
     */
    @Override
    public boolean updateNum(Blog blog) {
        if(blogDaoImp.updateInfo(blog)==1){
            return true;
        }
        return false;
    }

    /**
     * 查询评论数
     * @param articleId
     * @return
     */
    @Override
    public int selectCommentNumber(String articleId) {
        int x=0;
        x=blogDaoImp.countComment(articleId);
        return x;
    }

    @Override
    public int updateComment(String articleId) {
        int x=0;
        x=blogDaoImp.insertCountComment(articleId);
        return x;
    }

    /**
     * 模糊查询文章
     * @param info
     * @return
     */
    @Override
    public ArrayList<Blog> selectKey(String info) {
        ArrayList<Blog> blogs=new ArrayList<>();
        blogs=blogDaoImp.queryKey(info);
        if(blogs!=null&&blogs.size()>0){
            return blogs;
        }
        return null;
    }

    @Override
    public ArrayList<Blog> queryClassifyBlog(String email) {
        ArrayList<Blog> blogs=new ArrayList<>();
        blogs=blogDaoImp.queryClassify(email);
        if(blogs.size()>0&&blogs!=null){
            return blogs;
        }
        return null;
    }

    @Override
    public boolean verifyBlog(String id, int operate) {
        int x=blogDaoImp.updateBlogVerify(id,operate);
        if(x>0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<EchartsBean> queryClassifyCount(String email) {
        ArrayList<EchartsBean> echartsBeans=new ArrayList<>();
        echartsBeans=blogDaoImp.selectClassify(email);
        if(echartsBeans.size()>0&&echartsBeans!=null){
            return echartsBeans;
        }
        return null;
    }

    /**
     * 分页查询博客信息，参数为当前页和页面大小
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Blog> queryBlogByPage(int currentPage, int pageSize) {
       List<Blog> blogs=blogDaoImp.queryBlogByPage(currentPage,pageSize);
           PageBean<Blog> pageBean=new PageBean<>();
           int total=blogDaoImp.getTotalRecords();
            pageBean.setPageSize(pageSize);//设置页面大小
            pageBean.setTotalRecords(total);//设置总记录数
            pageBean.setCurrentPageNum(currentPage);//设置当前页
            int totalPageNum= total%pageSize==0?total/pageSize:(total/pageSize)+1;
            pageBean.setTotalPageNum(totalPageNum);//相除有除不尽的情况
            pageBean.setList(blogs);//设置当前页数据
            return pageBean;
    }
}
