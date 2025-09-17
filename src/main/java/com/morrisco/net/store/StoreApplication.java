package com.morrisco.net.store;

import com.morrisco.net.store.onlineStoreSystem.entities.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
        //SpringApplication.run(StoreApplication.class, args);//this is IOC Container for managing Beans or storage for objects
        //var userRegistrationService = applicationContext.getBean(UserService.class);
        //userRegistrationService.registerUser(new User("cheery",5,"rismocher","gg"));
//        var user =User.builder()
//                .name("a")
//                .email("b")
//                .password("c")
//                .build();
//        var address = Addresses.builder()
//                .city("mg")
//                .state("na")
//                .zipCode("67")
//                .street("kiptunoi")
//                .build();
       var products = Product.builder().name("alfha").price(BigDecimal.valueOf(10.8)).build();
        var product2 = Product.builder().name("beta").price(BigDecimal.valueOf(10.8)).build();
       var category = Category.builder().name("foods").build();
       category.addProduct(products);
        category.addProduct(product2);
        System.out.println(category);
	}

}
