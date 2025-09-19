package com.morrisco.net.store;

import com.morrisco.net.store.onlineStoreSystem.entities.*;
import com.morrisco.net.store.onlineStoreSystem.services.ProductService;
import com.morrisco.net.store.onlineStoreSystem.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
        ApplicationContext applicationContext= SpringApplication.run(StoreApplication.class, args);//this is IOC Container for managing Beans or storage for objects
       var repository= applicationContext.getBean(ProductService.class);
       repository.fetchProductsByRange(5,1);
        var user =User.builder()
                .name("a")
                .email("b")
                .password("c")
                .build();//transient state -> temporary
//        var address = Addresses.builder()
//                .city("mg")
//                .state("na")
//                .zipCode("67")
//                .street("kiptunoi")
//                .build();
//       var products = Product.builder().name("alfha").price(BigDecimal.valueOf(10.8)).build();
//        var product2 = Product.builder().name("beta").price(BigDecimal.valueOf(10.8)).build();

       //repository.save(user);
        //System.out.println(repository.findById(1).get().getEmail());
//        repository.findAll().forEach(user1 -> System.out.println(user1.getEmail()));
//        repository.deleteById(2);
	}

}
