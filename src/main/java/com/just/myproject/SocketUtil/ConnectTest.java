package com.just.myproject.SocketUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class ConnectTest {

    @Value("${socket.host}")
    String host;
    @Value("${socket.port}")
    int port;
    public Boolean getConn() throws IOException {
        Socket socket=new Socket(host, port);
        boolean s=socket.getKeepAlive();
        return s;
    }
}
