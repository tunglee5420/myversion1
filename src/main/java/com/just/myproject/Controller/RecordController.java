package com.just.myproject.Controller;

import com.just.myproject.Entity.Classroom;
import com.just.myproject.Entity.ErrorRecord;
import com.just.myproject.Service.UseRecordService;
import com.just.myproject.SocketUtil.Client;
import com.just.myproject.Utils.JsonData;
import com.just.myproject.Utils.JwtUtils;
import com.just.myproject.Utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/app/v1/record")
public class RecordController {
    @Autowired
    UseRecordService useRecordService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    Client client;

    @GetMapping("/setArea")
    public JsonData setArea(){
        Set<String> areas=useRecordService.setArea();
        if(areas!=null){
            return new JsonData(1,areas,null);
        }
        return new JsonData(0,null,"获取失败");
    }

    @GetMapping("/getRoomnum")
    public JsonData getRoomnum(@RequestParam("schoolarea") String schoolarea){
        List<Classroom> roomlist=useRecordService.getRoom(schoolarea);
        if(roomlist!=null){
            return new JsonData(1,roomlist,null);
        }
        return new JsonData(0,null,"请求失败");
    }
    @PostMapping("/online/borrow")
    public JsonData borrow(@RequestParam("roomnum") String roomnum,@RequestHeader Map<String ,String>header){
        String token=header.get("access_token");
        //System.out.println(token);
        Claims claims =JwtUtils.checkJWT(token);
        String worknum= (String) claims.get("worknum");
        //System.out.println(worknum);
        if( redisUtil.get(roomnum)!=null){
            return new JsonData(0,null,"教室已借出");
        }

        if(useRecordService.borrow(roomnum,worknum)!=0){
                return new JsonData(1,"借卡成功",null);
        }
        return  new JsonData(0,null,"借卡失败");
    }


    /**
     * 还卡接口
     * @param roomnum
     * @return
     */
    @PostMapping("/online/reBack")
    public JsonData reBacK(@RequestParam("roomnum") String roomnum,@RequestHeader(name="access_token") String token){
//        System.out.println(roomnum);

        Claims claims =JwtUtils.checkJWT(token);

        String worknum= (String) claims.get("worknum");
        if(worknum==null||roomnum==null){
            return new JsonData(0,null,"信息错误");
        }

        if(useRecordService.reBackCard(roomnum,worknum)!=0){
            return new JsonData(1,"还卡成功",null);
        }
        return new JsonData(0,null,"还卡失败");
    }
    @GetMapping("/online/reportError")
    public JsonData reportError(@RequestParam("errorInfo")String errorInfo,@RequestParam("roomnum")String roomnum,@RequestHeader(name="access_token") String token){

        Claims claims =JwtUtils.checkJWT(token);
        String worknum= (String) claims.get("worknum");
        if(errorInfo!=null&&roomnum!=null&&worknum!=null){
            ErrorRecord errorRecord=new ErrorRecord();
            errorRecord.setErrorInfo( errorInfo);
            errorRecord.setRoomnum(roomnum);
            errorRecord.setWorknum(worknum);
            if(useRecordService.reportErrorInfo(errorRecord)){
                return new JsonData(1,"提交成功，感谢",null);
            }
        }

        return new JsonData(0,null,"提交错误");
    }
    @GetMapping("/online/recommondClassroom")
    public List recommondClassroom(@RequestHeader(name="access_token") String token){
//        String token=header.get("access_token");
//        Claims claims =JwtUtils.checkJWT(token);
//        String worknum= (String) claims.get("worknum");
        List<Classroom> list=useRecordService.recommondRoomList();
        if(list==null||list.isEmpty()){
            return null;
        }
        return list;
    }
}
