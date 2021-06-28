package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.service.BlogServiceImp;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/editBlog")
public class EditBlog extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String blogId=req.getParameter("blogId");
       String partContent=req.getParameter("partContent");
       String content=req.getParameter("data");
       Date date=new Date();
       Blog blog=new Blog();
       blog.setId(blogId);
       blog.setPartContent(partContent);
       blog.setContent(content);
       blog.setPublish_time(date);
        JSONObject jsonObject=new JSONObject();
      if(blogServiceImp.updateBlog(blog)){
          //修改成功
          jsonObject.put("message",1);
          String url="http://localhost:8080/blog_war_exploded/article.jsp?blogId="+blogId;
          jsonObject.put("url",url);
          System.out.println("修改成功");
          PrintWriter printWriter=resp.getWriter();
          printWriter.write(jsonObject.toString());
      }
      else{
          //修改失败
          System.out.println("修改失败");
          String url="http://localhost:8080/blog_war_exploded/editblog.jsp?blogId="+blogId;
          jsonObject.put("message",0);
          jsonObject.put("url",url);
          PrintWriter printWriter=resp.getWriter();
          printWriter.write(jsonObject.toString());
      }
    }
}
