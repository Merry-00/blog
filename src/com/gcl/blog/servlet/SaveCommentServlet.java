package com.gcl.blog.servlet;
import com.gcl.blog.model.Comment;
import com.gcl.blog.service.BlogServiceImp;
import com.gcl.blog.service.CommentService;
import com.gcl.blog.service.CommentServiceImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;
@WebServlet("/saveComment")
public class SaveCommentServlet extends HttpServlet {
    private CommentService commentService;
    private BlogServiceImp blogServiceImp;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentContent = req.getParameter("comment");
        String commentId=UUID.randomUUID().toString();
        String email=req.getParameter("email");//前端传过来的信息可能不安全，不真实
        System.out.println("saveComment的email:"+email);
        HttpSession session=req.getSession();
        Object obj=session.getAttribute("email");
        PrintWriter out=resp.getWriter();
        if(obj==null){
            out.write("尚未登录，权限不足！");
        }
        else {
            String email1=(String)obj;
            String parentId=req.getParameter("parentId");
            Date date=new Date();
            String titleId=req.getParameter("titleId");
            Comment comment = new Comment(commentId,email,parentId,titleId,commentContent,date);
            commentService = new CommentServiceImp();
            blogServiceImp=new BlogServiceImp();
            int x=blogServiceImp.updateComment(titleId);
           boolean isSuccess = commentService.add(comment);

           if(isSuccess&&x>0){
           out.write("评论成功");
       }
       else{
           out.write("评论失败");
       }
        }
    }
}
