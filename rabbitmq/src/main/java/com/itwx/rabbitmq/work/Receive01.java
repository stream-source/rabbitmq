package com.itwx.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author:wx
 * @Date:2018/12/30 16:15
 */
public class Receive01 {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        //获取连接、mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        /**
         * 确认模式：手动模式，消息被消费者消费者消费以后要返回一个消费状态，
         * 期间服务器将消息状态标记为：不可用的状态（即存在队列中，不能被其他消费者消费）
         * 一直等待消费者返回消费状态；
         * 第二个参数：false为手动模式（ACK手动签收）
         */
        //监听队列，手动返回完成（手动签收ACK）
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '" + message +"'");
            Thread.sleep(10);

            //返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);


        }
    }
}
