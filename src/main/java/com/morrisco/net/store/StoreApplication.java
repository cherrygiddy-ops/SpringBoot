package com.morrisco.net.store;

import com.morrisco.net.store.notificationsManager.NotificationManager;
import com.morrisco.net.store.orderService.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(StoreApplication.class, args);//this is IOC Container for managing Beans or storage for objects
        var orderService = applicationContext.getBean(OrderService.class);
        orderService.makeOrder(5);

        var orderService2 = applicationContext.getBean(OrderService.class);
        orderService2.makeOrder(5);

        applicationContext.close();
	}

}
