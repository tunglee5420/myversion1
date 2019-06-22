package com.just.myproject.Service.Imp;

import com.just.myproject.Entity.UseRecord;
import com.just.myproject.Entity.User;
import com.just.myproject.Mapper.UserMapper;
import com.just.myproject.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;


    public int insertUser (User user){
//        System.out.println(user.toString());
        return userMapper.insertUser(user);
    }

    public List<User>findAll(){
        return userMapper.findAll();
    }

    public int resetPWD(User user){
        return userMapper.resetPWd( user);
    }
    public User userLogin(String worknum,String password){
//        System.out.println( userMapper.userLogin(worknum).toString());
        User user=null;

            user=userMapper.userLogin(worknum);

        if(user!=null&&password.equals(user.getPassword())){
            return user;
        }
        return null;
    }
    public int resetInfo(User user){
        return userMapper.resetInfo(user);
    }
    public User userCheck(String worknum){
        return userMapper.userLogin(worknum);
    }
    public List<Map<String,Object>> getMyRecordServ(String worknum){
        if(worknum==null) return null;
        return userMapper.getMyRecord(worknum);
    }
}
