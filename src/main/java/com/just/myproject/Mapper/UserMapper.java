package com.just.myproject.Mapper;

import com.just.myproject.Entity.UseRecord;
import com.just.myproject.Entity.User;
import com.just.myproject.Provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from user ")
    List<User> findAll();

    //注册个人信息和账户
    @InsertProvider( type = UserProvider.class,method = "insertUser")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertUser(User user);


    //设置昵称和头像

    int resetInfo(User user);

    //重设密码
    @UpdateProvider( type = UserProvider.class,method = "resetPassword")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int resetPWd(User user);

    //登陆
    @Select("select * from user where worknum=#{worknum}")
    @Results({ @Result(column = "head_img", property = "headimg"),
            @Result(column = "is_admin", property = "isAdmin")})
    User userLogin(String worknum);

    List<Map<String,Object>> getMyRecord(String worknum);
}
