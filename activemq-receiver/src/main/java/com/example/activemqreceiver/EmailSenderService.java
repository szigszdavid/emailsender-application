package com.example.activemqreceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("david.szigetkozi-szabo@futureofmedia.hu");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        mailSender.send(simpleMailMessage);

        log.info("Email sent");
    }

    public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachment) throws MessagingException {

        MimeMessage mimeMailMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

        mimeMessageHelper.setFrom("david.szigetkozi-szabo@futureofmedia.hu");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);

        FileSystemResource fileSystem = new FileSystemResource(new File(attachment));

        log.info("File found");

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);

        mailSender.send(mimeMailMessage);

        log.info("Email with attachment sent");

    }
}
