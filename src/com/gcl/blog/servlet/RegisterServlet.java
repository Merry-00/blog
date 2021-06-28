package com.gcl.blog.servlet;

import com.gcl.blog.model.Register;
import com.gcl.blog.service.UserService;
import com.gcl.blog.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    UserServiceImp userServiceImp=new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    /**
     *
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String email=req.getParameter("email");
       String password=req.getParameter("password");
       int root=1;
       Register register=new Register(email,password,root);
       boolean flag2=userServiceImp.queryUser(email);
        HttpSession session=req.getSession();
        session.setAttribute("email",email);
        session.setAttribute("user",register);
        System.out.println(flag2);//返回结果为true 说明用户不存在
       if(flag2){
           boolean flag=userServiceImp.addUser(register);
           //注册成功 进入完善基本信息页面
           if(flag){
               req.getRequestDispatcher("/info.jsp").forward(req,resp);
           }
           else{
               String message="注册失败";
               resp.setContentType("text/html;charset=UTF-8");
               PrintWriter out=resp.getWriter();
               out.println("<h1>" + message + "</h1>"+"<a href='./register.jsp'>点击返回注册</a>");
           }
       }
       else{
           String message="用户名已经存在";
           resp.setContentType("text/html;charset=UTF-8");
           PrintWriter out=resp.getWriter();
           out.println("<h1>" + message + "</h1>"+"<a href='./login.jsp'>点击返回登录</a>");
       }
    }
}
