package com.just.myproject.Utils;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class AllExnHandler {
    private static final org.slf4j.Logger LOG=  LoggerFactory.getLogger(AllExnHandler.class);
    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request){
        LOG.error("url{},msg{}",request.getRequestURI(),e.getMessage());
        Map<String,Object> map=new HashMap<>();
        map.put("code",500);
        map.put("errMsg",e.getMessage());
        map.put("url",request.getRequestURI());
        return map;
    }

}
