package com.gcl.blog.dao;

import com.gcl.blog.model.Blog;
import com.gcl.blog.model.User;
import com.gcl.blog.utils.DBUtil;
import com.gcl.blog.utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BlogDaoImp implements BlogDao{


    /**
     * 根据作者的email查找所有的博客
     * @param
     * @return
     */
    @Override
    public ArrayList<Blog> selectAll(int isOk) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        ArrayList<Blog> blogs=new ArrayList<>();
        //按照点赞数的多少查询所有的文章
        String sql="SELECT article.*,nickname \n" +
                "from article LEFT JOIN `user`\n" +
                "on  `user`.email=article.author_email \n" +
                "where is_ok=?\n" +
                "ORDER BY article.`like` DESC";
        try{
            connection = DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,isOk);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String id=resultSet.getString(1);
                String title=resultSet.getString(2);
                String content=resultSet.getString(3);
                String email=resultSet.getString(4);
                //将String转化为date
                String time=resultSet.getString(5);
                Date date=DateUtil.strToUtilDate(time);
                String label=resultSet.getString(6);
                String classify=resultSet.getString(7);
                int comment=resultSet.getInt(8);
                int like=resultSet.getInt(9);
                int collect=resultSet.getInt(10);
                int browse=resultSet.getInt(11);
                String partContent=resultSet.getString(13);
                String nickName=resultSet.getString(14);
                Blog blog=new Blog(id,title,content,email,date,label,classify,comment,like,collect,browse,isOk,partContent,nickName);
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * 插入blog进数据库中保存
     * @param blog
     * @return
     */
    @Override
    public int insertBlog(Blog blog) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into article(id,title,content,author_email,publish_time,label,classify,comment,`like`," +
                "collect,browse,is_ok,part_content) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            connection =DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,blog.getId());
            preparedStatement.setString(2,blog.getTitle());
            preparedStatement.setString(3,blog.getContent());
            preparedStatement.setString(4,blog.getAuthor_email());
            String publishTime=DateUtil.dateToStr(blog.getPublish_time());
            preparedStatement.setString(5,publishTime);
            preparedStatement.setString(6,blog.getLabel());
            preparedStatement.setString(7,blog.getClassify());
            preparedStatement.setInt(8,blog.getComment());
            preparedStatement.setInt(9,blog.getLike());
            preparedStatement.setInt(10,blog.getCollect());
            preparedStatement.setInt(11,blog.getBrowse());
            preparedStatement.setInt(12,blog.getIs_ok());
            preparedStatement.setString(13,blog.getPartContent());
            int x=preparedStatement.executeUpdate();
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @Override
    public int deleteBlog(String id) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="delete from article where id=?";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            int x=preparedStatement.executeUpdate();
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    /**
     * 根据文章id查找单篇博客
     * @param blogId
     * @return
     */
    @Override
    public Blog selectByPrimaryKey(String blogId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Blog blog = null;
        String sql = "select * from article where id='"+blogId+"'";
        try{
            connection = DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String id=resultSet.getString(1);
                String title=resultSet.getString(2);
                String content=resultSet.getString(3);
                String email=resultSet.getString(4);
                Date publishTime= DateUtil.strToUtilDate(resultSet.getString(5));
                String label=resultSet.getString(6);
                String classify=resultSet.getString(7);
                int comment=resultSet.getInt(8);
                int like=resultSet.getInt(9);
                int collect=resultSet.getInt(10);
                int browse=resultSet.getInt(11);
                int isOk=resultSet.getInt(12);
                String partContent=resultSet.getString(13);
                blog=new Blog(id,title,content,email,publishTime,label,classify,comment,like,collect,browse,isOk,partContent);
            }
            return blog;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * 查询某个用户的所有文章
     * @param email
     * @return
     */
    @Override
    public ArrayList<Blog> selectByEmail(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Blog blog = null;
        String sql = "select * from article where author_email=?";
        ArrayList<Blog> blogs=new ArrayList<>();
        try{
            connection = DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String id=resultSet.getString(1);
                String title=resultSet.getString(2);
                String content=resultSet.getString(3);
                String email1=resultSet.getString(4);
                Date publishTime= DateUtil.strToUtilDate(resultSet.getString(5));
                String label=resultSet.getString(6);
                String classify=resultSet.getString(7);
                int comment=resultSet.getInt(8);
                int like=resultSet.getInt(9);
                int collect=resultSet.getInt(10);
                int browse=resultSet.getInt(11);
                int isOk=resultSet.getInt(12);
                String partContent=resultSet.getString(13);
                blog=new Blog(id,title,content,email1,publishTime,label,classify,comment,like,collect,browse,isOk,partContent);
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public int updateBlog(Blog blog) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="update article set content=? , part_content=?,publish_time=? where id=?";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,blog.getContent());
            preparedStatement.setString(2,blog.getPartContent());
            preparedStatement.setString(3,DateUtil.dateToStr(blog.getPublish_time()));
            preparedStatement.setString(4,blog.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    /**
     * 更新博客的浏览量
     * @param blog
     * @return
     */
    @Override
    public int updateInfo(Blog blog) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="update article set browse=? where id=?";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,blog.getBrowse());
            preparedStatement.setString(2,blog.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    /**
     * 查询某一篇文章的评论数
     * @param titleId
     * @return
     */
    @Override
    public int countComment(String titleId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="select count(comment_id) from `comment` where title_id=?";
        int x=0;
        ResultSet resultSet=null;
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,titleId);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                x=resultSet.getInt(1);
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return 0;
    }

    /**
     * 将评论数插入到数据库中
     * @param titleId
     * @return
     */
    @Override
    public int insertCountComment(String titleId) {
        BlogDaoImp blogDaoImp=new BlogDaoImp();
        int x=blogDaoImp.countComment(titleId);
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="update article set comment=? where id=?";
        int y=0;
        if(x>0){
            try{
                connection=DBUtil.getConnection();
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setInt(1,x);
                preparedStatement.setString(2,titleId);
                y=preparedStatement.executeUpdate();
                return y;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtil.closeAll(connection,preparedStatement,null);
            }
        }
        return 0;
    }

    /**
     * 根据文章的标题进行模糊查询
     * @param info
     * @return
     */
    @Override
    public ArrayList<Blog> queryKey(String info) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="SELECT * FROM `article` where title like ?";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+info+"%");
            resultSet=preparedStatement.executeQuery();
            ArrayList<Blog> blogs=new ArrayList<>();
            while(resultSet.next()){
                Blog blog=new Blog();
                String id=resultSet.getString(1);
                String title=resultSet.getString(2);
                String content=resultSet.getString(3);
                String email1=resultSet.getString(4);
                Date publishTime= DateUtil.strToUtilDate(resultSet.getString(5));
                String label=resultSet.getString(6);
                String classify=resultSet.getString(7);
                int comment=resultSet.getInt(8);
                int like=resultSet.getInt(9);
                int collect=resultSet.getInt(10);
                int browse=resultSet.getInt(11);
                int isOk=resultSet.getInt(12);
                String partContent=resultSet.getString(13);
                blog=new Blog(id,title,content,email1,publishTime,label,classify,comment,like,collect,browse,isOk,partContent);
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<Blog> queryClassify(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="SELECT *,count(id) as count FROM `article` where author_email=? GROUP BY classify";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            resultSet=preparedStatement.executeQuery();
            ArrayList<Blog> blogs=new ArrayList<>();
            while(resultSet.next()){
                Blog blog=new Blog();
                String id=resultSet.getString(1);
                String title=resultSet.getString(2);
                String content=resultSet.getString(3);
                String email1=resultSet.getString(4);
                Date publishTime= DateUtil.strToUtilDate(resultSet.getString(5));
                String label=resultSet.getString(6);
                String classify=resultSet.getString(7);
                int comment=resultSet.getInt(8);
                int like=resultSet.getInt(9);
                int collect=resultSet.getInt(10);
                int browse=resultSet.getInt(11);
                int isOk=resultSet.getInt(12);
                String partContent=resultSet.getString(13);
                int count=resultSet.getInt(14);
                blog=new Blog(id,title,content,email1,publishTime,label,classify,comment,like,collect,browse,isOk,partContent,count);
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * 分类查询博客审核状态
     * @return
     */
    @Override
    public ArrayList<Blog> selectPublish() {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Blog blog = null;
        String sql = "SELECT *,count(is_ok) as count FROM `article`  GROUP BY is_ok";
        ArrayList<Blog> blogs=new ArrayList<>();
        try{
            connection = DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String id=resultSet.getString(1);
                String title=resultSet.getString(2);
                String content=resultSet.getString(3);
                String email1=resultSet.getString(4);
                Date publishTime= DateUtil.strToUtilDate(resultSet.getString(5));
                String label=resultSet.getString(6);
                String classify=resultSet.getString(7);
                int comment=resultSet.getInt(8);
                int like=resultSet.getInt(9);
                int collect=resultSet.getInt(10);
                int browse=resultSet.getInt(11);
                int isOk=resultSet.getInt(12);
                String partContent=resultSet.getString(13);
                int count=resultSet.getInt(14);
                blog=new Blog(id,title,content,email1,publishTime,label,classify,comment,like,collect,browse,isOk,partContent);
                blogs.add(blog);
            }
            return blogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public int updateBlogVerify(String id, int operate) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="update article set is_ok=? where id=?";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,operate);
            preparedStatement.setString(2,id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }
    //将评论数插入到数据库中




}
