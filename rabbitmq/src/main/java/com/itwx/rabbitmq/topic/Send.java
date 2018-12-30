package com.itwx.rabbitmq.topic;

import com.itwx.rabbitmq.ps.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @Author:wx
 * @Date:2018/12/30 17:48
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {

        //创建连接、mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 创建交换机：
         * 第一个参数：交换机名称
         * 第二个参数：交换机类型
         */
        //创建声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        String message = "Hello Exchange direct(路由模式)";
        /**
         * 发布消息：
         * 第一个参数：交换机名称
         * 第二个参数：routingkey:用于交换机与队列的匹配
         *
         */
        channel.basicPublish(EXCHANGE_NAME,"log.debug",null,message.getBytes());

        System.out.println("[x] Send"+message);
        channel.close();
        connection.close();
    }
}
