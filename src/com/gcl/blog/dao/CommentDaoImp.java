package com.gcl.blog.dao;

import com.gcl.blog.model.Comment;
import com.gcl.blog.utils.DBUtil;
import com.gcl.blog.utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDaoImp implements CommentDao {
    /**
     * 将评论信息插入数据库中
     * @param comment
     * @return
     */
    @Override
    public int insertActive(Comment comment) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into comment(comment_id,email,parent_id,title_id,content,comment_time) values(?,?,?,?,?,?)";
        String time= DateUtil.dateToStr(comment.getCommentTime());
        try{
            connection= DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,comment.getCommentId());
            preparedStatement.setString(2,comment.getEmail());
            preparedStatement.setString(3,comment.getParentId());
            preparedStatement.setString(4,comment.getTitleId());
            preparedStatement.setString(5,comment.getContent());
            preparedStatement.setString(6,time);
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
     * 根据主键删除评论
     * @param commentId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String commentId) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="delete from comment where comment_id=?";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,commentId);
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
     * 根据文章id查询所有的评论----查询到的是所有对博客的评论
     * @param titleId
     * @return
     */
    @Override
    public List<Comment> selectList(String titleId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select  comment.* ,nickname from comment \n" +
                "left join `user` on comment.email=`user`.email where title_id=? and parent_id=?";
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, titleId);
            preparedStatement.setString(2,titleId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String commentId = resultSet.getString(1);
                String email = resultSet.getString(2);
                String parentId = resultSet.getString(3);
                String content = resultSet.getString(5);
                String time = resultSet.getString(6);
                String nickName=resultSet.getString(7);
                Date commentTime = DateUtil.strToUtilDate(time);
                Comment comment = new Comment(commentId, email, parentId, titleId, content, commentTime,nickName);
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(connection, preparedStatement, resultSet);
        }
        return null;
    }

    /***
     *  查询到的是某篇博客所有对评论的回复
     * @param parentId
     * @param titleId
     * @return
     */
    @Override
    public List<Comment> selectList(String parentId,String titleId) {
        String sql = "select  comment.* ,nickname from comment \n" +
                "left join `user` on comment.email=`user`.email where title_id=? and parent_id=?";
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Comment> comments=new ArrayList<>();
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            //子评论的parentId=子评论的父级commentId
            preparedStatement.setString(1,titleId);
            preparedStatement.setString(2,parentId);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String commentId=resultSet.getString("comment_id");
                String email=resultSet.getString(2);
                String content=resultSet.getString(5);
                String time=resultSet.getString(6);
                String nickName=resultSet.getString(7);
                Date commentTime=DateUtil.strToUtilDate(time);
                Comment comment=new Comment(commentId,email,parentId,titleId,content,commentTime,nickName);
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }


    /**
     * 统计某一篇文章的评论数
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


}
