package com.itwx.rabbitmq.direct;

import com.itwx.rabbitmq.ps.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @Author:wx
 * @Date:2018/12/30 17:17
 */
public class Receive2 {
    private final static String QUEUE_NAME = "test_queue_direct02";
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        //创建连接、mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //将队列绑定到交换机上
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"info");

        //同一时刻服务器只发送一条消息
        channel.basicQos(1);

        //创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //消费消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] direct Receive02 '"+message + "'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
}
