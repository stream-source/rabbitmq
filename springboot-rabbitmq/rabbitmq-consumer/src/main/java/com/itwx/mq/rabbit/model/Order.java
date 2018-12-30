package com.itwx.mq.rabbit.model;

import java.io.Serializable;

/**
 * @Author:wx
 * @Date:2018/12/23 15:57
 */
public class Order implements Serializable {

    private String id;
    private String orderName;
    /**
     * 存储订单对应的消息Id唯一标识
     */
    private String messageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
