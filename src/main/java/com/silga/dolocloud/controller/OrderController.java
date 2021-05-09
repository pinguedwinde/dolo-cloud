package com.silga.dolocloud.controller;

import com.silga.dolocloud.model.DoloOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("orders")
public class OrderController {

    @GetMapping("current")
    public String orderForm(Model model) {
        model.addAttribute("doloOrder", new DoloOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid DoloOrder order, Errors errors) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("DoloOrder submitted: " + order);
        return "redirect:/";
    }
}
