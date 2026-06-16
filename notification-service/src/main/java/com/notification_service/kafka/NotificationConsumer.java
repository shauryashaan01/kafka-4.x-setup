package com.notification_service.kafka;

import com.notification_service.constants.AppConstants;
import com.notification_service.dto.OrderEvent;
import com.notification_service.service.EmailService;
import com.notification_service.service.SmsService;
import com.notification_service.service.WhatsappService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final EmailService emailService;
    private final SmsService smsService;
    private final WhatsappService whatsappService;

    public NotificationConsumer(EmailService emailService, SmsService smsService, WhatsappService whatsappService) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.whatsappService = whatsappService;
    }

    @KafkaListener(
            topics = AppConstants.TOPIC,
            groupId = "notification-group"
    )
    public void consume(OrderEvent orderEvent) {
        System.out.println("Received Order Event: " + orderEvent);
        if (orderEvent.getStatus().equals("ORDER_PLACED")) {
            System.out.println("Received Order Event: " + orderEvent.getEmail());
            emailService.sendEmail(orderEvent.getEmail(), "Transaction Complete", "Order Placed Successfully");
            smsService.sendSms(orderEvent.getMobile(),"Transaction Complete");
            whatsappService.sendWhatsapp(orderEvent.getWhatsapp(), "Transaction Complete");
        }else{
            emailService.sendEmail(orderEvent.getEmail(), "Transaction Incomplete", "Order Incomplete");
            smsService.sendSms(orderEvent.getMobile(), "Transaction Incomplete");
            whatsappService.sendWhatsapp(orderEvent.getWhatsapp(), "Transaction Incomplete");
        }

        String subject = "Order Status Update";

        String body = "Order Id: " + orderEvent.getOrderId() +
                "\nOrder Status: " + orderEvent.getStatus();
    }
}
