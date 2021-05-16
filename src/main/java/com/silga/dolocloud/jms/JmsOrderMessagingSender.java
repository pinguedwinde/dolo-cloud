package com.silga.dolocloud.jms;

import com.silga.dolocloud.model.DoloOrder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service
public class JmsOrderMessagingSender implements OrderMessagingSender {
    private final JmsTemplate jmsTemplate;
    private final Destination orderQueueDestination;

    public JmsOrderMessagingSender(JmsTemplate jmsTemplate, Destination orderQueueDestination) {
        this.jmsTemplate = jmsTemplate;
        this.orderQueueDestination = orderQueueDestination;
    }

    @Override
    public void sendOrder(DoloOrder order) {
        jmsTemplate.send(session -> session.createObjectMessage(order));
    }
    @Override
    public void sendOrderWithDestinationName(DoloOrder order) {
        jmsTemplate.send("dolocloud.order.queue", session -> session.createObjectMessage(order));
    }

    @Override
    public void sendOrderWithDestination(DoloOrder order) {
        jmsTemplate.send(orderQueueDestination, session -> session.createObjectMessage(order));
    }

    @Override
    public void convertAndSendOrder(DoloOrder order) {
        jmsTemplate.convertAndSend(order, message -> {
                message.setStringProperty("X_ORDER_SOURCE", "WEB");
                return message;
            }
        );
    }


}
