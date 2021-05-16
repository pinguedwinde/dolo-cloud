package com.silga.dolocloud.jms;

import com.silga.dolocloud.model.DoloOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderListener {
    private final KitchenUI ui;
    @Autowired
    public JmsOrderListener(KitchenUI ui) {
        this.ui = ui;
    }
    @JmsListener(destination = "dolocloud.order.queue")
    public void receiveOrder(DoloOrder order) {
        ui.displayOrder(order);
    }
}
