package com.gcl.blog.servlet;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/imageShow")
public class ImageShow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageFileName = req.getParameter("imageFileName");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setContentType("image/jpeg");
        OutputStream os = resp.getOutputStream();
        String savePath = req.getServletContext().getRealPath("/uploadFile");  //存储路径

        try (FileImageInputStream input = new FileImageInputStream(new File(savePath + File.separator + imageFileName));
             ByteArrayOutputStream output = new ByteArrayOutputStream();) {
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = input.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            byte[] data = output.toByteArray();
            os.write(data);
            os.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
