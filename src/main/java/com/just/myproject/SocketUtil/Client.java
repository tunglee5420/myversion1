package com.just.myproject.SocketUtil;

import com.just.myproject.Utils.JsonData;
import com.just.myproject.Utils.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;

/**
 * 客户端
 */
@Component
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class Client {
    @Value("${socket.host}")
    String host;
    @Value("${socket.port}")
    int port;
    public  int  sendMessage( JsonData jsonData){
        try {
            System.out.println(host);
            Socket socket=new Socket(host, port);

            PrintWriter out = new PrintWriter(socket.getOutputStream()); // 输出，to 服务器 socket
            out.write(JsonUtil.beanToJson(jsonData));
            out.flush(); // 刷缓冲输出，to 服务器
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            System.out.println(in.readLine());
            is.close();
            in.close();
            socket.close();
            if(out!=null){
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
