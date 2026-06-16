package com.notification_service.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone.number}")
    private String fromNumber;

    public String sendSms(String to, String messageBody) {

        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromNumber),
                messageBody).create();

        return "Message sent successfully. SID: " + message.getSid();
    }

}
