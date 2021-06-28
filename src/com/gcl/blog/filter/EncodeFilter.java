package com.gcl.blog.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class EncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String type = response.getContentType();
        if(type==null||"".equals(type)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
        } else if("application/x-msdownload".equals(type)||type.contains("image")){
            response.setContentType(type);
        }else{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {

    }
}
