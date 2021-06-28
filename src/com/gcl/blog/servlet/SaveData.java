package com.gcl.blog.servlet;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.Register;
import com.gcl.blog.service.BlogServiceImp;
import com.gcl.blog.utils.DateUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

@WebServlet("/saveData")
public class SaveData extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content=req.getParameter("data");
        String id= UUID.randomUUID().toString();
        String uuid=id.substring(0,16).replace("-","");
        String title=req.getParameter("title");
        Register register=(Register)req.getSession().getAttribute("user");
        String authorEmail=register.getEmail();
        Date date=new Date();
        String label=req.getParameter("label");
        System.out.println("label:"+label);
        String classify=req.getParameter("classify");
        String partContent=req.getParameter("partContent");
        int comment=0;
        int like=0;
        int collect=0;
        int browse=0;
        int isOk=0;
        Blog blog=new Blog(uuid,title,content,authorEmail,date,label,classify,comment,like,collect,browse,isOk,partContent
        );
        JSONObject jsonObject=new JSONObject();
        System.out.println(blog.toString());
        if(blogServiceImp.addBlog(blog)){
            System.out.println("文章发布成功");
            //查看文章
            jsonObject.put("message",1);
            //jsonObject.put("url","http://localhost:8080/blog_war_exploded/article.jsp?blogId="+uuid);
            jsonObject.put("url","http://localhost:8080/blog_war_exploded/readMyBlog?email="+authorEmail);
            PrintWriter out=resp.getWriter();
            out.write(jsonObject.toString());
//            resp.sendRedirect("http://localhost:8080/blog_war_exploded/article.jsp?blogId="+uuid);//跳转到查看文章页面
        }else{
            jsonObject.put("message",0);
            jsonObject.put("url","http://localhost:8080/blog_war_exploded/addblog.jsp");
            PrintWriter out=resp.getWriter();
            out.write(jsonObject.toString());
            System.out.println("文章添加失败");
        }


//        String savePath = req.getServletContext().getRealPath("/md");  //存储路径
//        File file = new File(savePath);
//        if(!file.exists()){
//            file.mkdirs();
//        }
//        FileWriter out = new FileWriter(savePath+"/demo.html");
//        out.write(data);
//        out.flush();
//        out.close();

    }
}
