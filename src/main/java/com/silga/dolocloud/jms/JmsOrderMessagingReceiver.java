package com.silga.dolocloud.jms;

import com.silga.dolocloud.model.DoloOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessagingReceiver implements OrderMessagingReceiver{
    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;
    private final String destinationName = "dolocloud.order.queue";

    @Autowired
    public JmsOrderMessagingReceiver(JmsTemplate jmsTemplate, MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public DoloOrder receiveOrder() throws JMSException {
        Message message = jmsTemplate.receive(destinationName);
        return (DoloOrder) messageConverter.fromMessage(message);
    }

    @Override
    public DoloOrder convertAndReceiveOrder() {
        return (DoloOrder) jmsTemplate.receiveAndConvert(destinationName);
    }
}
