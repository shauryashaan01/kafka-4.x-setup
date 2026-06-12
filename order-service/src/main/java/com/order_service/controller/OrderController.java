package com.order_service.controller;

import com.order_service.dto.OrderEvent;
import com.order_service.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String createOrder(@RequestBody OrderEvent orderEvent) {

        orderProducer.sendOrderEvent(orderEvent);

        return "Order Event Published Successfully";
    }
}
