package com.gcl.blog.dao;

import com.gcl.blog.model.Register;
import com.gcl.blog.utils.DBUtil;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现RegisterDao里面的方法
 */
public class RegisterDaoImp implements RegisterDao {
    /**
     * 根据email查询是否存在
     * 存在返回1 不存在返回0
     * @return
     */
    @Override
    public int selectByEmail(String email) {
        return selectByPrimaryKey(email)==null?0:1;
    }

    /**
     * 将数据写入注册表中
     * @param register
     * @return
     */
    @Override
    public int insertUser(Register register) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into register(email,password,root_id) values(?,?,?)";
        try{
            connection=DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,register.getEmail());
            preparedStatement.setString(2,register.getPassword());
            preparedStatement.setInt(3,register.getRoot_id());
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
     * 在登录时查询是否存在该对象
     * ResultSet:
     * 最初，指针被置于第一行之前。next 方法将指针移动到下一行；
     * 因为该方法在 ResultSet 对象中没有下一行时
     * 返回 false，所以可以在 while 循环中使用它来迭代结果集。
     * @param email
     * @return
     */
    @Override
    public Register selectByPrimaryKey(String email) {
        Register register =null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from register where email=?";
        try{
            connection= DBUtil.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){

                String password=resultSet.getString(2);
                int root=resultSet.getInt(3);
                register=new Register(email,password,root);
                return register;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeAll(connection,preparedStatement,resultSet);
        }
        return null;
    }

}
