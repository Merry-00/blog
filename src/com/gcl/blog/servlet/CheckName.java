package com.gcl.blog.servlet;

import com.gcl.blog.service.UserService;
import com.gcl.blog.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkName")
public class CheckName extends HttpServlet {
    UserServiceImp userServiceImp=new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickName=req.getParameter("nickName");
        System.out.println("nickName"+nickName);
       boolean flag=userServiceImp.queryNickName(nickName);
        PrintWriter out=resp.getWriter();
        System.out.println("是否存在用户名的flag:"+flag);
       if(flag){
           out.write("用户名已经存在！请重新输入...");
       }
       else{
           out.write("用户名可以使用");
       }


    }
}
