package com.notification_service.kafka;

import com.notification_service.constants.AppConstants;
import com.notification_service.dto.OrderEvent;
import com.notification_service.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = AppConstants.TOPIC,
            groupId = "notification-group"
    )
    public void consume(OrderEvent orderEvent) {
        if (orderEvent.getStatus().equals("ORDER_PLACED")) {
            System.out.println("Received Order Event: " + orderEvent.getEmail());
            emailService.sendEmail(orderEvent.getEmail(), "Test", "WELCOME");
        }

        String subject = "Order Status Update";

        String body = "Order Id: " + orderEvent.getOrderId() +
                "\nOrder Status: " + orderEvent.getStatus();
    }
}
