package com.example.activemqreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class ActivemqReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqReceiverApplication.class, args);
	}

}
