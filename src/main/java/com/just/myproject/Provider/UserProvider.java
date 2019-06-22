package com.just.myproject.Provider;

import com.just.myproject.Entity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String insertUser(User user){
        return new SQL(){
            {
                INSERT_INTO("user");
                if (user.getWorknum() != null) {
                    VALUES("worknum", "#{worknum}");
                }
                if (user.getName() != null) {
                    VALUES("name", "#{name}");
                }
                if (user.getPhone() != null) {
                    VALUES("phone", "#{phone}");
                }
                if (user.getPassword() != null) {
                    VALUES("password", "#{password}");
                }
                if (user.getDepartment() != null) {
                    VALUES("department", "#{department}");
                }
                if (user.getEmail() != null) {
                    VALUES("email", "#{email}");
                }
                if (user.getNickname() != null) {
                    VALUES("nickname", "#{nickname}");
                }
                if (user.getHeadimg() != null) {
                    VALUES("head_img", "#{headimg}");
                }

            }
        }.toString();
    }


    public String resetPassword(User user){
        return new SQL(){
            {
                UPDATE("user");
                if(user.getPassword()!=null){
                    SET("password=#{password}");
                }if(user.getEmail()!=null&&user.getWorknum()!=null){
                    WHERE("email=#{email} and worknum=#{worknum}");
                }

            }
        }.toString();
    }

}
