package com.morrisco.net.store.notificationsManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {
    private final NotificationService notificationService;

    public NotificationManager(@Qualifier("email")NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendMessage(String message){
        notificationService.send(message);
    }
}
