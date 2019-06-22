package com.just.myproject.Service;

import com.just.myproject.Entity.Classroom;
import com.just.myproject.Entity.ErrorRecord;
import com.just.myproject.Entity.UseRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UseRecordService {

    /**
     * 查询教室区域
     */
    Set<String> setArea();

    /**
     * 获取每个区域的教室编号
     * @param schoolarea
     * @return
     */
    List<Classroom> getRoom(String schoolarea );

    /**
     * 根据教室编号和工号借卡
     * @param roomnum
     * @param worknum
     * @return
     */
    int borrow(String roomnum,String worknum);
    /**
     * 还卡
     * @param roomnum
     * @param worknum
     * @return
     */
    int reBackCard(String roomnum,String worknum);

    boolean reportErrorInfo(ErrorRecord errorRecord);
    List recommondRoomList();


}
