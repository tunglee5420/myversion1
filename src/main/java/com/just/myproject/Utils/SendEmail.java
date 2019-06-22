package com.just.myproject.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class SendEmail {

    @Autowired
    JavaMailSender mailSender ;
    @Value("${spring.mail.username}")
    public  String myEmailAccount;


    public String sendEmail(String receiver){
        String code="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String idcode="";
        int k;
        for (int i=0;i<4;i++){
            k=new Random().nextInt(52);
            idcode+=code.charAt(k);
        }

        SimpleMailMessage message =new SimpleMailMessage();
        message.setTo(receiver);
        message.setSubject("验证码");
        message.setText(idcode);
        message.setFrom(myEmailAccount);
        mailSender.send(message);
        return idcode;
    }
}
