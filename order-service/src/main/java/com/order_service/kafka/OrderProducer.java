package com.order_service.kafka;

import com.order_service.constants.AppConstants;
import com.order_service.dto.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(AppConstants.TOPIC, orderEvent.getOrderId().toString(), orderEvent);

        System.out.println("Order event sent to topic: " + orderEvent);
    }
}
