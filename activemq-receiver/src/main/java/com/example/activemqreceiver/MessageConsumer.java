package com.example.activemqreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@EnableJms
public class MessageConsumer {

    @Autowired
    private EmailSenderService emailSenderService;

    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = "test-queue")
    public void listener(String contactDTO){
        logger.info("Message received {} ", contactDTO);

        String[] datas = contactDTO.split(",");

        logger.info("Split: {} {} {}", datas[0], datas[1], datas[2]);

        String sendTo = datas[0];//contactDTO.getEmailAddress();
            String body = "Welcome " + datas[1] + " " + datas[2] + "!\n" + "You have been registered to " + datas[3] + ".";
            emailSenderService.sendEmail(sendTo,"Welcome email",body);
            //emailSenderService.sendEmailWithAttachment("david.szigetkozi-szabo@futureofmedia.hu","This is subject","This is body","C:\\Users\\FOM-Dev-7\\Downloads\\Junior Java.pdf");
    }
}