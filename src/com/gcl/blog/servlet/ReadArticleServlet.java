package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.User;
import com.gcl.blog.service.BlogService;
import com.gcl.blog.service.BlogServiceImp;
import com.gcl.blog.service.UserServiceImp;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/readBlog")
public class ReadArticleServlet extends HttpServlet {
        private BlogService blogService;
        UserServiceImp userServiceImp=new UserServiceImp();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req,resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String blogId = req.getParameter("blogId");
            System.out.println("blogId"+blogId);
            blogService = new BlogServiceImp();
            Blog blog = blogService.getBlogById(blogId);
            if(blog!=null){
                User user=userServiceImp.query(blog.getAuthor_email());
            }
            req.setAttribute("blog",blog);
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(JSONObject.fromObject(blog).toString());
        }
    }
