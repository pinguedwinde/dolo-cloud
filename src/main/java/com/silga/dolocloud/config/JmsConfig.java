package com.silga.dolocloud.config;

import com.silga.dolocloud.model.DoloOrder;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    @Bean
    public JmsTemplate jmsTemplate (){
        return new JmsTemplate();
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("dolocloud.order.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("order", DoloOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
}
