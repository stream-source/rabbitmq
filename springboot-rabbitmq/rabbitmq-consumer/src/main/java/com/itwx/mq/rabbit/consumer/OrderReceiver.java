package com.itwx.mq.rabbit.consumer;

import com.itwx.mq.rabbit.model.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Author:wx
 * @Date:2018/12/23 16:03
 */
@Component
public class OrderReceiver {
    private static Logger logger = LoggerFactory.getLogger(OrderReceiver.class);

    /**
     * @RabbitListener 该注解除了可以监听，还可以创建交换机（exchange）、队列（queue）之间的绑定
     * 监听：需要绑定队列、交换机、路由键
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(value = "order-exchange", durable = "true", type = "topic"),
            key = "order.#"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String, Object> headers,
                               Channel channel) throws IOException {
        //消费者操作

        logger.info("-------收到消息，开始消费----------");
        logger.info("订单ID：{}", order.getId());

        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliverTag, false);
    }
}
