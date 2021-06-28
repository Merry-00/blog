package com.gcl.blog.servlet;

import com.gcl.blog.model.Register;
import com.gcl.blog.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/login")
public class Login extends HttpServlet {
    UserServiceImp userServiceImp = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Register register = new Register(email, password);

        if (userServiceImp.loginUser(register)) {
            register = userServiceImp.loginGetRole(register.getEmail());
            HttpSession session=req.getSession();
            session.setAttribute("email",email);
            //登录成功 跳转到个人博客系统
            req.getSession().setAttribute("user",register);
            //创建session对象
//            req.getRequestDispatcher("").forward(req,resp);//消息转发sessionScope\requestScope
            if(register.getRoot_id()==1){
                resp.sendRedirect("http://localhost:8080/blog_war_exploded/main.jsp");
            }
           else{
                resp.sendRedirect("http://localhost:8080/blog_war_exploded/manager.jsp");
            }
            //重定向类似于window.location.href=""
        } else {
            //登录失败 返回重新登录
            String message = "登录失败";
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>" + message + "</h1>" + "<a href='./index.jsp'>点击返回登录</a>");
        }
    }
}
