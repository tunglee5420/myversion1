package com.just.myproject.Service;

import com.just.myproject.Entity.UseRecord;
import com.just.myproject.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    int insertUser (User user);
    List<User> findAll();
    int resetPWD(User user);
    User userLogin(String worknum,String password);
    int resetInfo(User user);
    User userCheck(String worknum);
    List<Map<String,Object>> getMyRecordServ(String worknum);
}
