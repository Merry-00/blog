package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.PageBean;
import com.gcl.blog.service.BlogService;
import com.gcl.blog.service.BlogServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listPage")
public class ListPage extends HttpServlet {
    private BlogService blogService=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取当前页
        String currentPage = req.getParameter("currentPage");
        //第一次访问，默认currentPage访问第一页
        if (currentPage == null) {
            currentPage = "1";
        }
        Integer currentPageNum = Integer.parseInt(currentPage);
        //页面大小设置成固定值4
        //分页查询所有
        PageBean<Blog> pageBean = blogService.queryBlogByPage(currentPageNum, 4);
        if(pageBean!=null){
            //servlet重在业务逻辑，取值传参JSP侧重于页面显示
            req.setAttribute("page", pageBean);
        }
        else{
            String message="博客列表为空";
            req.setAttribute("message",message);
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
