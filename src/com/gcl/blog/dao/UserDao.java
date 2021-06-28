package com.gcl.blog.dao;

import com.gcl.blog.model.Register;
import com.gcl.blog.model.User;

import javax.servlet.http.Part;
import java.util.ArrayList;

public interface UserDao {
    int insert(User user);
    User selectByEmail(String email);
    int addPicture(Part part);
    int updateBlogInfo(User user);
    ArrayList<User> selectAll();
    int insertUserInfo(User user);
    int updateData(User user);
    String selectNickName(String nickName);
    //修改密码
    int updatePsd(Register register);

}
