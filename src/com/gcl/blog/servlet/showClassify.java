package com.gcl.blog.servlet;

import com.gcl.blog.model.EchartsBean;
import com.gcl.blog.service.BlogServiceImp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showClassify")
public class showClassify extends HttpServlet {
    BlogServiceImp blogServiceImp=new BlogServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        ArrayList<EchartsBean> echartsBeans=new ArrayList<>();
        //EchartsBean为name和values
        echartsBeans=blogServiceImp.queryClassifyCount(email);
        //将list集合转化成json字符串格式
        JSONArray jsonArray=JSONArray.fromObject(echartsBeans);
        String json=jsonArray.toString();
        req.setAttribute("list",json);
        //req.getRequestDispatcher("/showData.jsp").forward(req,resp);
    }
}
