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

@WebServlet("/readMyBlog")
public class ReadMyBlog extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        ArrayList<Blog> blogs=blogServiceImp.queryMyBlog(email);
        if(blogs==null ||blogs.size()==0){
            String message="您还没有发布过文章！";
            req.setAttribute("messege",message);
            req.getRequestDispatcher("/title.jsp").forward(req,resp);
        }
        else if(blogs.size()!=0){
            req.setAttribute("blogs",blogs);
            req.getRequestDispatcher("/title.jsp").forward(req,resp);
        }
    }
}
