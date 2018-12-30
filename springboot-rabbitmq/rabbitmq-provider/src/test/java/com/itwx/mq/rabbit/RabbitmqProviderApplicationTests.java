package com.itwx.mq.rabbit;

import com.itwx.mq.rabbit.model.Order;
import com.itwx.mq.rabbit.provider.OrderSender;
import com.itwx.mq.rabbit.utils.UUIdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProviderApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderSender orderSender;

    @Test
    public void send1() {
        Order order = new Order();
        // order.setId(UUID.randomUUID().toString().replace("-",""));
        order.setId(UUIdUtil.getUUId());
        order.setOrderName("订单测试一");
        order.setMessageId(System.currentTimeMillis() + "$" + UUIdUtil.getUUId());

        orderSender.send(order);
    }

}

