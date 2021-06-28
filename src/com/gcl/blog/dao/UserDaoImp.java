package com.gcl.blog.dao;

import com.gcl.blog.model.Register;
import com.gcl.blog.model.User;
import com.gcl.blog.utils.DBUtil;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImp implements UserDao{
    /**
     * 将从前端获取到的数据插入user表中
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int x=0;
        String sql="insert into user(email,nickname,sex,img,fans,focus,comment_num,like_num,collect_num,browse_num) values(?,?,?,?,?,?,?,?,?,?)";
        try{
            connection= DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getNickName());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getImg());
            preparedStatement.setInt(5,user.getFans());
            preparedStatement.setInt(6,user.getFocus());
            preparedStatement.setInt(7,user.getCommentNum());
            preparedStatement.setInt(8,user.getLikeNum());
            preparedStatement.setInt(9,user.getCollectNum());
            preparedStatement.setInt(10,user.getBrowseNum());
            x=preparedStatement.executeUpdate();
             return x;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    /**
     * 根据email查询用户信息
     * @param email
     * @return
     */
    @Override
    public User selectByEmail(String email) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from user where email=?";

        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String email1=resultSet.getString(1);
                String nickName=resultSet.getString(2);
                String sex=resultSet.getString(3);
                String img=resultSet.getString(4);
                int fans=resultSet.getInt(5);
                int focus=resultSet.getInt(6);
                int commentNum=resultSet.getInt(7);
                int likeNum=resultSet.getInt(8);
                int collectNum=resultSet.getInt(9);
                int browseNum=resultSet.getInt(10);
                User user=new User(email1,nickName,sex,img,fans,focus,commentNum,likeNum,collectNum,browseNum);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     *  实现上传头像的功能
     * @param part
     * @return
     */
    @Override
    public int addPicture(Part part) {
        //1. 上传逻辑
        //获取文件名
        String filename=part.getSubmittedFileName();
        String serverpath="D:\\java code\\IdeaProjects\\blog\\web\\img\\";
        //获取上传文件的文件名
        InputStream in = null;
        try {
            in = part.getInputStream();
            FileOutputStream fos = new FileOutputStream(serverpath + filename);
            byte[] bytes=new byte[1024];
            int length=0;
            while((length=in.read(bytes))!=-1){
                fos.write(bytes,0,length);
            }
            fos.close();
            in.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 修改博客的基本信息---评论数，收藏，点赞，关注量，粉丝数
     * @param user
     * @return
     */
    @Override
    public int updateBlogInfo(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        return 0;
    }

    /**
     * 查询所有的用户信息
     * @return
     */
    @Override
    public ArrayList<User> selectAll() {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from user";
        ArrayList<User> users=new ArrayList<>();
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String email=resultSet.getString(1);
                String nickName=resultSet.getString(2);
                String sex=resultSet.getString(3);
                String img=resultSet.getString(4);
                int fans=resultSet.getInt(5);
                int focus=resultSet.getInt(6);
                int commentNum=resultSet.getInt(7);
                int likeNum=resultSet.getInt(8);
                int collectNum=resultSet.getInt(9);
                int browseNum=resultSet.getInt(10);
                User user=new User(email,nickName,sex,img,fans,focus,commentNum,likeNum,collectNum,browseNum);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     *  将用户基本信息添加到user表中
     * @param user
     * @return
     */
    @Override
    public int insertUserInfo(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into user(email,nickname,sex,img,fans,focus,comment_num,like_num,collect_num,browse_num) values(?,?,?,?,?,?,?,?,?,?)";
        try{
            connection =DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getNickName());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getImg());
            preparedStatement.setInt(5,user.getFans());
            preparedStatement.setInt(6,user.getFocus());
            preparedStatement.setInt(7,user.getCommentNum());
            preparedStatement.setInt(8,user.getLikeNum());
            preparedStatement.setInt(9,user.getCollectNum());
            preparedStatement.setInt(10,user.getBrowseNum());
            int x=preparedStatement.executeUpdate();
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    @Override
    public int updateData(User user) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="update article set browse_num=?  where email=?";
        try{
            connection =DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getBrowseNum());
            preparedStatement.setString(2,user.getEmail());
            int x=preparedStatement.executeUpdate();
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }

    @Override
    public String  selectNickName(String nickName) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from user where nickname=?";
        String name="";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,nickName);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                name=resultSet.getString(2);
                System.out.println("后端查询到的nickName:"+name);
            }
            return name;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public int updatePsd(Register register) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="update register set password=?  where email=?";
        try{
            connection =DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,register.getPassword());
            preparedStatement.setString(2,register.getEmail());
            int x=preparedStatement.executeUpdate();
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,null);
        }
        return 0;
    }
}
