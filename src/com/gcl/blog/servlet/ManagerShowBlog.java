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

@WebServlet("/managerShowBlog")
public class ManagerShowBlog extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       ArrayList<Blog> blogs=new ArrayList<>();
       int operate = Integer.parseInt( req.getParameter("operate"));
       String url= "";
       switch (operate){
           case 0: //没有审核的博客列表
               blogs=blogServiceImp.selectAllBlog(0);
               url = "/isPublish.jsp";
               String message="未审核博客为"+(blogs==null?0:blogs.size());
               req.setAttribute("message",message);
               break;
           case 1://审核通过的博客列表
               blogs=blogServiceImp.selectAllBlog(1);
               url="/publishBlog.jsp";
               String message1="审核通过博客数为"+(blogs==null?0:blogs.size());
               req.setAttribute("message",message1);
               break;
           case 2:  //审核未通过的列表
               blogs=blogServiceImp.selectAllBlog(2);
               url="/notpublishblog.jsp";
               String message2="审核不通过博客数为"+(blogs==null?0:blogs.size());
               req.setAttribute("message",message2);
               break;
           default:
               break;
       }
       if(blogs!=null||blogs.size()>0){
           req.setAttribute("blogs",blogs);
       }
        req.getRequestDispatcher(url).forward(req,resp);
    }
}
