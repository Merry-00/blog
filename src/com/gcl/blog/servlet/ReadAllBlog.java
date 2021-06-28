package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.service.BlogServiceImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/readAllBlog")
public class ReadAllBlog extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        int rootId=1;
        ArrayList<Blog> blogs=blogServiceImp.selectAllBlog(rootId);
        String email=req.getParameter("email");
        if(blogs==null || blogs.size()==0){
            String message="没有文章列表";
            req.setAttribute("message",message);
            if(email==null){
                req.getRequestDispatcher("/").forward(req,resp);
            }
            else{
                req.getRequestDispatcher("./readblog.jsp").forward(req,resp);
            }
        }
        else if(blogs.size()>0){
            req.setAttribute("blogs",blogs);
            if(email==null){
                req.getRequestDispatcher("/").forward(req,resp);
            }
            else{
                req.getRequestDispatcher("./readblog.jsp").forward(req,resp);
            }
        }
    }
}
