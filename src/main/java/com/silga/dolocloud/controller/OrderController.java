package com.silga.dolocloud.controller;

import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.model.User;
import com.silga.dolocloud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("orders")
@ConfigurationProperties(prefix = "dolo.orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private int pageSize = 20;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("current")
    public String orderForm(Model model) {
        model.addAttribute("doloOrder", new DoloOrder());
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser( @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, pageSize);
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user,pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid DoloOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        order = orderRepository.save(order);
        log.info("DoloOrder submitted: " + order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
