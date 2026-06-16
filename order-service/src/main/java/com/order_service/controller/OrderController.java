package com.order_service.controller;

import com.order_service.dto.OrderEvent;
import com.order_service.kafka.NotificationProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final NotificationProducer notificationProducer;

    public OrderController(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    @PostMapping("/orders")
    public String createOrder(@RequestBody OrderEvent orderEvent) {

        notificationProducer.sendOrderEvent(orderEvent);

        return "Order Event Published Successfully";
    }
}
