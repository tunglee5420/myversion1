package com.just.myproject.Mapper;

import com.just.myproject.Entity.Classroom;
import com.just.myproject.Entity.ErrorRecord;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper

public interface RecordMapper {

    /**
     * 查询教室区域
     * @return set
     */
    @Select("select distinct  schoolarea from classroom")
    Set<String> setArea();

    /**
     * 获取每个区域的教室编号
     * @param schoolarea
     * @return List
     */
    @Select("select * from classroom where schoolarea=#{schoolarea}")
    @Results({ @Result(column = "is_use", property = "isUse")})
    List<Classroom> getRoomList(String schoolarea );

    /**
     *
     * @param map
     * @return
     */
    int insertBorrowRecord(Map map);

    int changeUse(Map map);

    @Select( "select * from classroom where roomnum=#{roomnum}")
    Classroom getRoom(String roomnum);

    /**
     * 还卡
     * @param id
     * @return
     */
    int reBack(int id);

    int insertErrorInfo(ErrorRecord errorRecord);


    @Select(" select * from classroom where roomnum in(select room from roomlist where date=#{date} and class_order=#{classOrder})")
    @Results({ @Result(column = "is_use", property = "isUse")})
    List<Classroom>getUseRoom(@Param("date") String date ,@Param("classOrder") int classOrder);
}
