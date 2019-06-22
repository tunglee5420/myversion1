package com.just.myproject.Service;

import com.just.myproject.Entity.ErrorRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface AdminService {
    boolean fileParse(MultipartFile file);
    boolean importInfo(MultipartFile file);
    boolean setAdminServ(String roomnum,int k);
//    boolean setUseServ(String roomnum,int s);
    List<Map<String, Object>> getRecord(Map<String,Object> conditonMap);
    List<ErrorRecord > getErrorInfo();
}

