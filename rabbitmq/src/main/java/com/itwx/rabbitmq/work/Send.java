package com.itwx.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:wx
 * @Date:2018/12/30 16:06
 */
public class Send {
    private final static String QUEUE_NAME = "test_work_queue";
    public static void main(String[] args) throws Exception {
        //创建连接、mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false, false,null);

        for (int i = 0; i < 50; i++){
            String message = "message[" + i+"]";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("[x]:Send["+i+"]");
            Thread.sleep(10);
        }
        channel.close();
        connection.close();

    }

}
