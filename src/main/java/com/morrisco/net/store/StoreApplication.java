package com.morrisco.net.store;

import com.morrisco.net.store.notificationsManager.NotificationManager;
import com.morrisco.net.store.onlineStoreSystem.entities.User;
import com.morrisco.net.store.orderService.OrderService;
import com.morrisco.net.store.userRegistrationService.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
        //SpringApplication.run(StoreApplication.class, args);//this is IOC Container for managing Beans or storage for objects
        //var userRegistrationService = applicationContext.getBean(UserService.class);
        //userRegistrationService.registerUser(new User("cheery",5,"rismocher","gg"));
        var user =User.builder()
                .name("a")
                .email("b")
                .password("c")
                .build();
        System.out.println(user.toString());
	}

}
