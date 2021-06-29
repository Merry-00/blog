package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.Register;
import com.gcl.blog.service.BlogServiceImp;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showData")
public class showData extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        List<Blog> blogs=null;
        blogs=blogServiceImp.queryMyBlog(email);
        PrintWriter out=resp.getWriter();
        if(blogs!=null&&blogs.size()>0){
            req.setAttribute("blogs",blogs);
            JSONArray jsonArray=JSONArray.fromObject(blogs);
            out.write(jsonArray.toString());
        }
        else{
            String message="没有数据";
            out.write(message);
        }
    }
}
