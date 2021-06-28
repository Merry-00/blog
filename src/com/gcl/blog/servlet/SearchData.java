package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.service.BlogService;
import com.gcl.blog.service.BlogServiceImp;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

@WebServlet("/searchData")
public class SearchData extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //解决中文乱码问题：
        //请求数据的中文乱码问题
            req.setCharacterEncoding("UTF-8");//以get方式提交数据时，request设置编码无效。即使设置了UTF-8还是会去查ISO8859-1
            String info = req.getParameter("info");
            System.out.println("infor:"+info);
            ArrayList<Blog> blogs=new ArrayList<>();
            blogs=blogServiceImp.selectKey(info);
            PrintWriter out=resp.getWriter();
            if(blogs!=null){
                req.setAttribute("blogs",blogs);
                req.getRequestDispatcher("/search.jsp").forward(req,resp);
       }
      else{
          String message="查询结果为空";
          out.write(message);
       }
    }
}
