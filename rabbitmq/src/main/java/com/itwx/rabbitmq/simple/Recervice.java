package com.itwx.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:wx
 * @Date:2018/12/30 15:17
 */
public class Recervice {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        //获取连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明（创建队列）
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        /**
         * 确认模式：自动模式，无论消费者是否将消费成功，服务器都认为该消息被消费者消费成功（会将消息移除队列中）
         */
        //监听队列:消费者一直监听队列，一有消息则就开始消费，改程序一直在运行
        channel.basicConsume(QUEUE_NAME,true,consumer);

        //获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '" + message +"'");
        }

    }
}
