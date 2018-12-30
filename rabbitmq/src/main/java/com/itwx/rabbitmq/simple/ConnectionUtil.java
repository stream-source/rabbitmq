package com.itwx.rabbitmq.simple;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:wx
 * @Date:2018/12/30 15:28
 */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //mq的主机地址
        factory.setHost("192.168.40.129");
        factory.setPort(5672);
        //设置虚拟主机、用户、密码
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        //通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
