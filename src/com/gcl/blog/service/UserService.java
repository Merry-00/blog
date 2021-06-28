package com.gcl.blog.service;

import com.gcl.blog.model.Register;
import com.gcl.blog.model.User;

import javax.servlet.http.Part;
import java.sql.SQLException;

public interface UserService {
    //个人中心
    //1.用户注册功能
    boolean addUser(Register register);
    boolean queryUser(String email);
    //2.用户登录功能
    boolean loginUser(Register register) throws SQLException;
    //实现上传头像功能
    int addPicture(Part part);
    //完善基本信息功能
    boolean addInfo(User user);
    //查询用户基本信息
    User query(String email);
    //查询用户名是否存在
    boolean queryNickName(String nickName);
    //3.修改密码功能
    boolean updatePassword(Register register);
    //4.可访问路径功能

}
