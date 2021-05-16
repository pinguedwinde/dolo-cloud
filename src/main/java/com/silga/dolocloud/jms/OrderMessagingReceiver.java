package com.silga.dolocloud.jms;

import com.silga.dolocloud.model.DoloOrder;

import javax.jms.JMSException;

public interface OrderMessagingReceiver {
    DoloOrder receiveOrder() throws JMSException;
    DoloOrder convertAndReceiveOrder() throws JMSException;
}
