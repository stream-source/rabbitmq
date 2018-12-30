package com.itwx.mq.rabbit.provider;

import com.itwx.mq.rabbit.model.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:wx
 * @Date:2018/12/23 15:18
 */
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        /**
         * exchang:virtual host中的exchange
         * routingKey:路由key
         * Object：消息体对象
         * correlationData:消息唯一标识Id,对象中只有id一个属性
         */
        rabbitTemplate.convertAndSend("order-exchange",
                "order.abcd",
                order,
                correlationData);
    }
}
