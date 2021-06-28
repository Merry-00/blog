package com.gcl.blog.servlet;

import com.gcl.blog.service.BlogServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/verifyBlog")
public class verifyBlog extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("blogId");
        int operate=Integer.parseInt(req.getParameter("operate"));
        boolean flag=blogServiceImp.verifyBlog(id,operate);
        PrintWriter out=resp.getWriter();
        if(flag){
            System.out.println("修改博客状态成功");
            out.write("修改博客状态成功");
        }
        else{
            System.out.println("修改博客状态失败");
            out.write("修改博客状态失败");
        }


    }
}
