package com.just.myproject.Mapper;
import com.just.myproject.Entity.ErrorRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    int importExcel(List list);
    int importCalander(List list);
    @Update("update user set is_admin=1 where worknum=#{worknum}")
    int setAdmin(String worknum);
    @Update("udate classroom set is_use =#{s} where roomunm=#{roomunm} ")
    int setUse(@Param("roomnum") String roomnum, @Param("s") int s);

    List<Map<String,Object>> getRecords(Map<String,Object> conditionMap);
    @Select(" select  * from error_record;")
    List<ErrorRecord>getErrorInfo();
}

