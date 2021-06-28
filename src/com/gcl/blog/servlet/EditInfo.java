package com.gcl.blog.servlet;

import com.gcl.blog.model.Register;
import com.gcl.blog.model.User;
import com.gcl.blog.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
@WebServlet("/editInfo")
public class EditInfo extends HttpServlet {
    UserServiceImp userServiceImp=new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Object obj=session.getAttribute("email");
        String email=obj.toString();
        System.out.println("从session中获取到的email:"+email);
        String nickName=req.getParameter("nickName");
        System.out.println("nickName:"+nickName);
        String sex=req.getParameter("sex");
        Part part = req.getPart("photo");
        int fans=0;
        int focus=0;
        int commentNum=0;
        int likeNum=0;
        int collectNum=0;
        int browseNum=0;
        System.out.println("part:"+part);
        int x=userServiceImp.addPicture(part);
        String img="img/"+part.getSubmittedFileName();
        User user =new User(email,nickName,sex,img,fans,focus,commentNum,likeNum,collectNum,browseNum);
        session.setAttribute("user",user);
        System.out.println(user.toString());
        if(userServiceImp.addInfo(user)){
            System.out.println("完善个人信息成功");
            //跳转到登录界面
            resp.sendRedirect("http://localhost:8080/blog_war_exploded/login.jsp");
        }
        else{
            System.out.println("完善个人信息失败");
            resp.sendRedirect("http://localhost:8080/blog_war_exploded/info.jsp");
        }
    }
}
