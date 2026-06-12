package com.notification_service.kafka;

import com.notification_service.constants.AppConstants;
import com.notification_service.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(
            topics = AppConstants.TOPIC,
            groupId = "notification-group"
    )
    public void consume(OrderEvent orderEvent) {
        System.out.println("Received Order Event: " + orderEvent.getEmail());

        String subject = "Order Status Update";

        String body = "Order Id: " + orderEvent.getOrderId() +
                "\nOrder Status: " + orderEvent.getStatus();
    }
}
