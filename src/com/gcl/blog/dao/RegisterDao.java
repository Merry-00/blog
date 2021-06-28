package com.gcl.blog.dao;

import com.gcl.blog.model.Register;

import java.sql.ResultSet;

/**
 * 用户完成登录所需要的数据库操作
 */
public interface RegisterDao {
    int selectByEmail(String email);
   int insertUser(Register register);
   Register selectByPrimaryKey(String email);
}
