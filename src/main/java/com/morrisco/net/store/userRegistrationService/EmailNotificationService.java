package com.morrisco.net.store.userRegistrationService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service()
@Primary
public class EmailNotificationService implements NotificationService{
    private User user;

    @Value("${emailService.host}")
    private String host;
    @Value("${emailService.port}")
    private String port;

    public EmailNotificationService(User user) {
        this.user = user;
    }

    @Override
    @PostConstruct
    public void send(String message, String mail) {
        System.out.println("getting host" + host);
        System.out.println("starting connections at this " + port);
        System.out.println("sending "+ message +" to" + mail);
    }
}
