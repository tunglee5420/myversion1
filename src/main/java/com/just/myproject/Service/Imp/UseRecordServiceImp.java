package com.just.myproject.Service.Imp;

import com.just.myproject.Entity.Classroom;
import com.just.myproject.Entity.ErrorRecord;

import com.just.myproject.Entity.UseRecord;
import com.just.myproject.Mapper.RecordMapper;

import com.just.myproject.Mapper.UserMapper;
import com.just.myproject.Service.UseRecordService;
import com.just.myproject.SocketUtil.Client;
import com.just.myproject.SocketUtil.ConnectTest;
import com.just.myproject.Utils.JsonData;

import com.just.myproject.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Resource
public class UseRecordServiceImp implements UseRecordService {
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    Client client;
    @Autowired
    ConnectTest connectonTest;
    /**
     * 教室区域
     * @return
     */
    public Set<String> setArea(){
        return recordMapper.setArea();
    }

    /**
     * 根据教室区域获得教室编号
     * @param schoolarea
     * @return
     */
    public List<Classroom> getRoom(String schoolarea ){
        return recordMapper.getRoomList(schoolarea);
    }

    /**
     * 借卡信息记录
     * @param roomnum
     * @param worknum
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public synchronized int borrow(String roomnum,String worknum){
        Map<String,Object> map=new HashMap<>();
        map.put("roomnum",roomnum);
        map.put("worknum",worknum);
        map.put("id",null);

        Map<String,Object> map1=new HashMap<>();
        map1.put("roomnum",roomnum);
        map1.put("code",0);
        map1.put("code1",1);

        try {

            int pre=client.sendMessage(new JsonData(1,roomnum,null));
            if(pre!=0){
                int f=recordMapper.insertBorrowRecord(map);
                int k= recordMapper.changeUse(map1);
                int res=client.sendMessage(new JsonData(2,roomnum,null));
                if(k!=0&&f!=0&&res!=0){
                    Map<String,Object> map2=new HashMap<>();
                    map2.put("worknum",worknum);
                    String id=String.valueOf(map.get("id"));
                    map2.put("recordId",id);
                    redisUtil.setMap(roomnum,map2);
                    return 1;
                }
            }
            return 0;

        }catch (Exception e){

        }
        return 0;
    }

    
    /**
     * 还卡
     * @param roomnum
     * @param worknum
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int reBackCard(String roomnum,String worknum){
        Map map=redisUtil.getMap(roomnum);
        int id=Integer.parseInt((String) map.get("recordId"));
        System.out.println(id);
        Map<String ,Object> map1=new HashMap<>();
        map1.put("code",1);
        map1.put("roomnum",roomnum);
        if( worknum.equals(map.get("worknum"))){
            try {
                if(client.sendMessage(new JsonData(0,roomnum,null))!=0){
                    if(recordMapper.changeUse(map1)!=0&& recordMapper.reBack(id)!=0){
                        redisUtil.deleteKey(worknum);
                        return 1;
                    }
                }
                   
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 提交报障信息
     * @param errorRecord
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean reportErrorInfo(ErrorRecord errorRecord){
        Map<String,Object> map=new HashMap<>();
        map.put("roomnum",errorRecord.getRoomnum());
        map.put("code1",0);
        map.put("code",0);
        int t= recordMapper.changeUse(map);
        int k=recordMapper.insertErrorInfo(errorRecord);
        if(k!=0&&t!=0){
            return true;
        }
        return false;
    }

    /**
     * 推荐教室列表
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List recommondRoomList(){
        List<Classroom> recomlist=new ArrayList<>();
        Set<String>areas=recordMapper.setArea();

        for (String area : areas) {
            List<Classroom> list=recordMapper.getRoomList(area);
            for (Classroom classroom : list) {
                if(classroom.getIsUse()==1&&classroom.getHealth()==1){
                    recomlist.add(classroom);
                }
            }
        }
        Calendar calendar= Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_WEEK);
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String d1=df.format(date);
        int k=belongCalendar(new Date());
        List<Classroom> use_list=recordMapper.getUseRoom("2019-03-11",k);
        for (Classroom s : use_list) {
            if(recomlist.contains(s)){
                recomlist.remove(s);
            }
        }
        return recomlist;
    }
    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @return
     */
     static int belongCalendar(Date nowTime) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        long t1 = System.currentTimeMillis() + 1000 * 60 * 30;

        Date bt0=null,et0=null,bt1 = null,et1 = null,bt2 = null,et2 = null,bt3 = null,et3= null,bt4= null,et4= null,et5=null,bt5=null,bt6=null,et6=null,et7=null,bt7=null,et8=null,bt8=null;

        try {
            nowTime = df.parse(df.format(new Date(t1)));
            bt0 = df.parse("00:00");
            et0 = df.parse("7:59");
            bt1 = df.parse("08:00");
            et1 = df.parse("9:45");
            bt2 = df.parse("10:00");
            et2 = df.parse("11:40");
            bt3 = df.parse("14:00");
            et3 = df.parse("15:40");
            bt4 = df.parse("16:00");
            et4 = df.parse("17:40");
            bt5 = df.parse("19:00");
            et5 = df.parse("20:40");
            bt6= df.parse("20:40");
            et6= df.parse("24:00");
            bt7= df.parse("17:41");
            et7=df.parse("18:59");
            bt8=df.parse("11:41");
            et8=df.parse("13:59");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(bt1);

        Calendar end = Calendar.getInstance();
        end.setTime(et1);

        if (date.after(begin) && date.before(end)) {
            return 1;
        }
        begin.setTime(bt2);
        end.setTime(et2);
        if (date.after(begin) && date.before(end)){
            return 2;
        }
        begin.setTime(bt3);
        end.setTime(et3);
        if(date.after(begin) && date.before(end)){
            return 3;
        }
        begin.setTime(bt4);
        end.setTime(et4);
        if(date.after(begin) && date.before(end)){
            return 4;
        }
        begin.setTime(bt5);
        end.setTime(et5);
        if(date.after(begin) && date.before(end)){
            return 5;
        }
        begin.setTime(bt0);
        end.setTime(et0);
        if(date.after(begin) && date.before(end)){
            return 0;
        }
        begin.setTime(bt6);
        end.setTime(et6);
        if(date.after(begin) && date.before(end)){
            return 0;
        }
        begin.setTime(bt7);
        end.setTime(et7);
        if(date.after(begin) && date.before(end)){
            return 0;
        }
        begin.setTime(bt8);
        end.setTime(et8);
        if(date.after(begin) && date.before(end)){
            return 0;
        }
        return 0;
    }

}
