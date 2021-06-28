package com.gcl.blog.filter;

import com.gcl.blog.model.Register;
import com.gcl.blog.service.UserServiceImp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;
@WebFilter({"/login","/main.jsp"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest request=(HttpServletRequest) servletRequest;
            HttpServletResponse response=(HttpServletResponse) servletResponse;
            Object obj=request.getSession().getAttribute("user");
            String email=null;
            String password=null;
            String autologin=null;
            //先判断，现在session中还有没有register对象
            if(obj!=null){
                filterChain.doFilter(request,response);
            }else {
                //session失效了
                Cookie[] cookies=request.getCookies();
                if (null != cookies) {
                    for (Cookie cookie : cookies) {
                        if("autologin".equals(cookie.getName())){
                            autologin=cookie.getValue();
                            System.out.println("autologin:"+autologin);
                        }
                        if("email".equals(cookie.getName())){
                            email= URLDecoder.decode(cookie.getValue());
                            System.out.println("email:"+email);
                        }
                        if("psd".equals(cookie.getName())){
                            password=new String(Base64.getDecoder().decode(cookie.getValue()), "UTF-8");
                            System.out.println("解码后的密码"+password);
                        }
                    }
                    if("true".equals(autologin)){
                        Register register1=new Register(email,password);
                        UserServiceImp userServiceImp=new UserServiceImp();
                        boolean flag=userServiceImp.loginUser(register1);
                        if(flag){
                            request.getSession().setAttribute("user", register1);
                            filterChain.doFilter(request,response);
                        }
                        else{
                           response.sendRedirect("http://localhost:8080/blog_war_exploded/login.jsp");
                        }
                    }
                    else{
                        filterChain.doFilter(request,response);
                    }
                }
                else{
                    filterChain.doFilter(request,response);
                }
            }
    }

    @Override
    public void destroy() {

    }
}
