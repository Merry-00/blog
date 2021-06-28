package com.gcl.blog.servlet;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@MultipartConfig    //标识Servlet支持文件上传
@WebServlet("/imageUpload")
public class ImageUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Message message=new Message();
        JSONObject res = new JSONObject();
       req.setCharacterEncoding("utf-8");
       resp.setCharacterEncoding("utf-8");
       resp.setContentType("application/json;charset=utf-8");

        String savePath = req.getServletContext().getRealPath("/uploadFile");  //存储路径
        System.out.println("savePath:"+savePath);
        File path = new File(savePath);
        if (!path.exists()) { //如果路径不存在则创建
            path.mkdirs();
        }

        Collection<Part> parts =req.getParts(); //获取上传的文件集合
        if (parts != null && parts.size() == 1) {   //上传单个文件
            Part part = req.getPart("editormd-image-file"); //-----① 通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            String header = part.getHeader("content-disposition");//获取请求头
            String fileName = getFileName(header);
            System.out.println("fileName"+fileName);
            String url = savePath + File.separator + fileName;
            part.write(url);           //把文件写到指定路径

            res.put("success", 1);
            res.put("message", "上传成功");
            res.put("url", "http://localhost:8080/blog_war_exploded/imageShow?imageFileName=" + fileName);
        } else {

            res.put("success", 0);
            res.put("message", "上传失败");

        }
        PrintWriter out = resp.getWriter();
        out.println(res.toString());
        out.flush();
        out.close();
    }

    /**
     * 根据请求头解析出文件名
     * @param header 请求头
     * @return 文件名
     */
    public String getFileName(String header) {

        String[] temp = header.split(";")[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = temp[1].substring(temp[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }
}
