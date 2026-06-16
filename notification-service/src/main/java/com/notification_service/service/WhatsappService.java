package com.notification_service.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    public String sendWhatsapp(String to, String messageBody) {

        Message message = Message.creator(
                new PhoneNumber("whatsapp:"+to),
                new PhoneNumber(fromNumber),
                messageBody
        ).create();

        return "Message sent successfully. SID: " + message.getSid();
    }

}
