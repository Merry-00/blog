package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.User;
import com.gcl.blog.service.BlogServiceImp;
import com.gcl.blog.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showInfo")
public class ShowInfo extends HttpServlet {
    UserServiceImp userServiceImp=new UserServiceImp();
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        User user=new User();
        user=userServiceImp.query(email);
        ArrayList<Blog> blogs=new ArrayList<>();
        blogs=blogServiceImp.queryClassifyBlog(email);
        req.setAttribute("user",user);
        req.setAttribute("blogs",blogs);
        if(user!=null){
            req.getRequestDispatcher("/showInfo.jsp").forward(req,resp);
        }
        else{
            System.out.println("请先完善基本信息");
            //跳转到完善基本信息
            req.getRequestDispatcher("/info.jsp").forward(req,resp);
        }
    }
}
