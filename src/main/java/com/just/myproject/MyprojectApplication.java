package com.just.myproject;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;


import org.springframework.transaction.annotation.EnableTransactionManagement;


//@EnableScheduling//开启定时任务
@SpringBootApplication
@MapperScan("com.just.myproject.Mapper")
@EnableConfigurationProperties
@ServletComponentScan
@EnableTransactionManagement
public class MyprojectApplication {


    public static void main(String[] args) {

        SpringApplication.run(MyprojectApplication.class, args);
    }

}
