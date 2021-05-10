package com.silga.dolocloud.controller;

import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("current")
    public String orderForm(Model model) {
        model.addAttribute("doloOrder", new DoloOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid DoloOrder order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        order = orderRepository.save(order);
        log.info("DoloOrder submitted: " + order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
