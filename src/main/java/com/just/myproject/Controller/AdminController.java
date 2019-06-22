package com.just.myproject.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.just.myproject.Entity.ErrorRecord;
import com.just.myproject.Entity.UseRecord;
import com.just.myproject.Entity.User;
import com.just.myproject.Service.AdminService;
import com.just.myproject.Service.UserService;
import com.just.myproject.Utils.JsonData;
import com.just.myproject.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/app/v1/admin/online")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @GetMapping("/findAllUser")
    public Object findAll(@RequestParam(value = "page",defaultValue = "1") int page,
                          @RequestParam(value = "size",defaultValue = "30")int size){
        PageHelper.startPage(page,size);
        List<User> list=userService.findAll();
        PageInfo<User> pageInfo=new PageInfo<>(list);
        return  pageInfo;
    }
    @PostMapping("/importClasstable")
    public JsonData setClassTable( @RequestParam("classtable") MultipartFile file){

        if(adminService.fileParse(file)){
            return new JsonData(1,"导入成功",null);
        }
        return new JsonData(0,null,"导入错误");
    }
    @PostMapping("/importCalander")
    public JsonData importCalander( @RequestParam("calander") MultipartFile file){

        if(adminService.importInfo(file)){
            return new JsonData(1,"导入成功",null);
        }
        return new JsonData(0,null,"导入错误");
    }
    @GetMapping("/setAdmin")
    public JsonData setAdmin(@RequestParam("worknum") String worknum,@RequestHeader(name="access_token") String token){

        Claims claims =JwtUtils.checkJWT(token);
        int k=(Integer) claims.get("isAdmin");
        if(k<2||worknum==null){
            return new JsonData(0,"您没有权限",null);
        }
        boolean flag=adminService.setAdminServ(worknum,k);
        if(flag==true)
            return new JsonData(1,"设置成功",null);
        return new JsonData(0,null,"设置失败");
    }
    @GetMapping("/setUse")
    public JsonData setUse(@RequestParam("roomnum") String roomnum, @RequestHeader(name="access_token") String token, @Param("status")int status){

        Claims claims =JwtUtils.checkJWT(token);
        int k=(Integer) claims.get("isAdmin");

        String worknum= (String) claims.get("worknum");

        if(roomnum==null||k==0){
            return new JsonData(0,null,"权限无效");
        }
        boolean flag=adminService.setAdminServ(roomnum,status);
        if(flag){
            return new JsonData(1,null,null);
        }
        return new JsonData(0,null,"错误");
    }
    @GetMapping("/getRecord")
    public JsonData getRecord(HttpServletRequest request){
        List<String> paraList=new ArrayList<>();
        paraList.add("cardReturn");
        paraList.add("roomnum");
        paraList.add("name");
        paraList.add("borrowTime");
        paraList.add("returnTime");
        paraList.add("schoolarea");
        paraList.add("worknum");
        try {
            request.setCharacterEncoding("UTF-8");
            Enumeration enu=request.getParameterNames();
            Map<String ,Object> conditionMap =new HashMap<>();

            while(enu.hasMoreElements()){
                String paraName=(String)enu.nextElement();
                if(paraList.contains(paraName)){
                    conditionMap.put(paraName,request.getParameter(paraName));
                }else
                    return new JsonData(0,null,"字段错误");

            }
            List<Map<String, Object>>list=adminService.getRecord(conditionMap);
            return list.isEmpty()?new JsonData(0,null,"无此纪录！"):new JsonData(1,list,null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new JsonData(0,null,"错误");

    }
    @GetMapping("/getErrorInfo")
    public JsonData getErrorInfo(){
        List<ErrorRecord> errlist=adminService.getErrorInfo();
        if(errlist!=null){
            return new JsonData(1,errlist,null);
        }
        return new JsonData(0,null,"内部错误");
    }


}
