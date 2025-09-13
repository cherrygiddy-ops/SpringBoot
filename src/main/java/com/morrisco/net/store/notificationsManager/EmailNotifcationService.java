package com.morrisco.net.store.notificationsManager;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
public class EmailNotifcationService implements NotificationService{
    @Override
    public void send(String message) {
        System.out.println("sending notifications using email");
    }
}
