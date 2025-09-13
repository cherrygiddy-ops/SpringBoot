package com.morrisco.net.store;

import com.morrisco.net.store.notificationsManager.NotificationManager;
import com.morrisco.net.store.orderService.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(StoreApplication.class, args);//this is IOC Container for managing Beans or storage for objects
        var notificationManager = applicationContext.getBean(NotificationManager.class);
        notificationManager.sendMessage("Hello world");
	}

}
