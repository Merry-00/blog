package com.gcl.blog.servlet;

import com.gcl.blog.model.Comment;
import com.gcl.blog.service.CommentServiceImp;
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

/**
 * 这里不是回复评论吗？是啊
 */
@WebServlet("/replyComment")
public class ReplyComment extends HttpServlet {
    CommentServiceImp commentServiceImp=new CommentServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleId=req.getParameter("articleId");
        String parentId=req.getParameter("parentId");
        System.out.println("执行到这了吗");
        List<Comment> comments=new ArrayList<>();
        comments=commentServiceImp.list(parentId,articleId);
        if(comments!=null&&comments.size()>0) {
            JSONArray jsonlist=JSONArray.fromObject(comments);
            PrintWriter out=resp.getWriter();
            out.write(jsonlist.toString());
        }
        else{
            System.out.println("评论为空");
            String message="还没有人评论";
            PrintWriter out=resp.getWriter();
            out.write(message);
        }//你前端没有进行到底有没有查到的判断
    }
}
