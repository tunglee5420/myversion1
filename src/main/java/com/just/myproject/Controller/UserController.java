package com.just.myproject.Controller;

import com.just.myproject.Entity.UseRecord;
import com.just.myproject.Entity.User;
import com.just.myproject.Service.UserService;
import com.just.myproject.Utils.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/app/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    FileUtils fileUtils;
    //注册信息

    @PostMapping("/register")
     public JsonData register(User user, @RequestParam("headImg")MultipartFile file){
        if(user!=null&&user.getWorknum()!=null&&user.getName()!=null&&user.getPhone()!=null&&user.getPassword()!=null
                &&user.getDepartment()!=null&&user.getEmail()!=null&&file!=null){
            String path= this.fileUtils.upload(file,user.getWorknum());
            user.setHeadimg(path);
            if(userService!=null&&userService.userCheck(user.getWorknum())==null){
                int res= userService.insertUser(user);
                if(res!=0){
                    return new JsonData(res,user.getWorknum(),null);
                }
            }else {
                return new JsonData(0,null,"用户已注册");
            }

        }
        return new JsonData(0,null,"注册失败");
     }
     //修改用户信息
    @PostMapping("/online/resetInfo")
    public JsonData resetInfo(User user,@RequestParam("headImg")MultipartFile file,@RequestHeader(name="access_token") String token){

        String path= fileUtils.upload(file,user.getWorknum());
        user.setHeadimg(path);

        Claims claims =JwtUtils.checkJWT(token);
        String worknum= (String) claims.get("worknum");

        if(user!=null||user.getHeadimg()!=null||user.getNickname()!=null||user.getWorknum()!=null||user.getPhone()!=null||user.getPassword()!=null||user.getEmail()!=null){

            if(userService.resetInfo(user)!=0){
                User user1=userService.userCheck(worknum);
                return new JsonData(1,user1,null);
            }
        }
        return new JsonData(0,null,"修改失败");
    }


    /**
     * 重设密码
     * @param idCode
     * @param worknum
     * @param password
     * @param email
     * @return
     */
     @PostMapping("/resetpwd")
     public JsonData checkCode(@RequestParam("idCode") String idCode, @RequestParam("worknum") String worknum,@RequestParam("password") String password,@RequestParam("email") String email ){

        if (idCode!=null&&worknum!=null&&password!=null){
            User user=new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setWorknum(worknum);
            String code= String.valueOf(redisUtil.getAndDelete(worknum));
            if(idCode.equals(code)){
                int k= userService.resetPWD(user);
                if(k!=0){
                    return new JsonData(k,null,"修改成功");
                }
                return new JsonData(k,null,"修改失败");
            }
        }
        return new JsonData(0,null,"验证错误");

     }
     @GetMapping ("checkCode")
     public JsonData resetpwd(@RequestParam("email") String email, @RequestParam("worknum") String worknum){

        if(email!=null&&worknum!=null&&userService!=null&&sendEmail!=null&&redisUtil!=null){
            if(userService.userCheck(worknum)!=null){
                String code=sendEmail.sendEmail(email);
                boolean a=redisUtil.set(worknum,code,50);
                return new JsonData(1,null,null);
            }
            return new JsonData(0,null,"用户不存在");
        }
        return new JsonData(0,null,"修改失败");
     }
    //用户登陆
    @PostMapping("/userlogin")
    public JsonData userlogin(@RequestParam("account") String worknum,@RequestParam("password") String password){

        if(worknum!=null&&password!=null){
            User user=userService.userLogin(worknum,password);
            if(user!=null){
                String token=JwtUtils.creatJwt(user);
                if(token!=null&&redisUtil.set(worknum,token,60*60*24)){
                    Map<String,String> map=new HashMap<>();
                    map.put("token",token);
                    map.put("nickName",user.getNickname());
                    map.put("headImg",user.getHeadimg());
                    map.put("Admin",String.valueOf(user.getIsAdmin()));
                    return new JsonData(1,map,null);
                }
            }
            return new JsonData(0,null,"用户名或者密码错误");
        }
        return new JsonData(0,null,"登陆信息为空");
     }
     @PostMapping("/online/getMyUseInfo")
    public List <Map<String,Object>> getMyUseInfo(@RequestHeader(name="access_token") String token){

         Claims claims =JwtUtils.checkJWT(token);
         String worknum= (String) claims.get("worknum");
         if(worknum==null) throw  new MyException("token 有误");
         return  userService.getMyRecordServ(worknum);
     }
}
