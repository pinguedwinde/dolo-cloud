package com.silga.dolocloud.jms;

import com.silga.dolocloud.model.DoloOrder;

public interface OrderMessagingSender {
    void sendOrder(DoloOrder order);
    void sendOrderWithDestinationName(DoloOrder order);
    void sendOrderWithDestination(DoloOrder order);
    void convertAndSendOrder(DoloOrder order);
}
