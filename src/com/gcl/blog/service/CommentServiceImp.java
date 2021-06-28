package com.gcl.blog.service;
import com.gcl.blog.dao.CommentDao;
import com.gcl.blog.dao.CommentDaoImp;
import com.gcl.blog.model.Comment;
import java.util.ArrayList;
import java.util.List;
public class CommentServiceImp implements CommentService{


    private CommentDao commentDao;

    /**
     * 将评论加入数据库
     * @param comment
     * @return
     */
    @Override
    public boolean add(Comment comment) {
        commentDao=new CommentDaoImp();
        if(commentDao.insertActive(comment)>0){
            return true;
        }
        return false;
    }

    /**
     * 根据文章id插入
     * @param articleId
     * @return
     */
    @Override
    public List<Comment> list(String articleId) {
        commentDao=new CommentDaoImp();
        List<Comment> comments=new ArrayList<>();
        comments=commentDao.selectList(articleId);
        if(comments!=null&&comments.size()>0){
            return comments;
        }
        return null;
    }

    /**
     * 根据子评论查询
     * @param parentId
     * @param articleId
     * @return
     */
    @Override
    public List<Comment> list(String parentId,String articleId) {
        commentDao=new CommentDaoImp();
        List<Comment> comments=new ArrayList<>();
        comments=commentDao.selectList(parentId,articleId);
        if(comments!=null&&comments.size()>0){
            return comments;
        }
        return null;
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Override
    public boolean remove(String commentId) {
        commentDao=new CommentDaoImp();
        if(commentDao.deleteByPrimaryKey(commentId)>0){
            return true;
        }
        return false;
    }
}
