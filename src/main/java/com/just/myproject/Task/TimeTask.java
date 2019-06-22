package com.just.myproject.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务业务类
 */
@Component
public class TimeTask {
//    @Scheduled(fixedRate = 2000)
    public void test(){
        System.out.println("定时任务");
    }
}
