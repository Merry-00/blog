package com.gcl.blog.test;

import com.gcl.blog.model.Blog;
import com.gcl.blog.service.BlogServiceImp;
import org.junit.Test;

import java.util.ArrayList;

public class selectAllBlogTest {
    @Test
    public void selectAll(){
        ArrayList<Blog> blogs=new ArrayList<Blog>();
        BlogServiceImp blogServiceImp=new BlogServiceImp();
        blogs=blogServiceImp.selectAllBlog(0);
        for(Blog blog:blogs){
            System.out.println(blog.getId());
            System.out.println(blog.getAuthor_email());
            System.out.println(blog.getBrowse());
            System.out.println(blog.getLike());
            System.out.println("--------");
        }
    }
}
