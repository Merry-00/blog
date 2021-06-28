package com.gcl.blog.service;

import com.gcl.blog.dao.RegisterDaoImp;
import com.gcl.blog.dao.UserDaoImp;
import com.gcl.blog.model.Register;
import com.gcl.blog.model.User;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class UserServiceImp implements UserService{
    RegisterDaoImp registerDaoImp=new RegisterDaoImp();
    UserDaoImp userDaoImp=new UserDaoImp();

    /**
     * 将注册信息写入注册表中，完成注册功能
     * @param register
     * @return
     */
    @Override
    public boolean addUser(Register register) {
            int x=registerDaoImp.insertUser(register);
            if(x==1){
                return true;
            }
            return false;
        }

    /**
     *  存在用户则返回false 否则返回true
     * @param email
     * @return
     */
    @Override
    public boolean queryUser(String email) {
        return registerDaoImp.selectByEmail(email)>0?false:true;
    }

    /**
     * 查询注册表中的账号密码与输入的是否匹配
     * @param register
     * @return
     * @throws SQLException
     */
    @Override
    public boolean loginUser(Register register) {
        Register register1= registerDaoImp.selectByPrimaryKey(register.getEmail());
        if(register1!=null){
            return register.getPassword().equals(register1.getPassword());
        }
        return false;
    }

    public Register loginGetRole(String email) {
        Register register= registerDaoImp.selectByPrimaryKey(email);
        return register;
    }
    @Override
    public int addPicture(Part part) {
        //1. 上传逻辑
        //获取文件名
        String filename=part.getSubmittedFileName();
        System.out.println("fileName:"+filename);
        String serverpath="D:\\java code\\IdeaProjects\\Query\\web\\img\\";
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

    @Override
    public boolean addInfo(User user) {
        if(userDaoImp.insertUserInfo(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public User query(String email) {
        UserDaoImp userDaoImp=new UserDaoImp();
        User user=userDaoImp.selectByEmail(email);
        if(user!=null){
            return user;
        }
        return null;
    }

    @Override
    public boolean queryNickName(String nickName) {
       String name=userDaoImp.selectNickName(nickName);
           if(nickName.equals(name)){
               return true;
           }
        return false;
    }

    @Override
    public boolean updatePassword(Register register) {
        int x=userDaoImp.updatePsd(register);
        if(x>0){
            return true;
        }
        return false;
    }
}
