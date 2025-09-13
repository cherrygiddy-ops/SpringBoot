package com.morrisco.net.store.orderService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * types of annotations
 * 1.@Container -> general purpose annotations (telling spring to manage objects of type OrderService)
 * 2.@Service ->this is for the classes that contain business logic
 * 3.@repository -> for classes that interact with databases
 * 4.@Controllers -> for marking classes used for handling webRequest
 * 5.@AutoWired -> used when we have constructor overload and want to specify the exact constructor to use to create that class
 * 6.@primary -> for marking the default bean especially when a class  has more than one implementations.
 * 7.@Qualify ->when one  to use specific implementation
 * 8.@PostConstruct //This is simply telling framework that execute this code after ordersevice class is initialize
 *                   //Useful since after initialization of an object then we can perform network connections ,database connections etc
 * 9.@PreDestroy  //This annotations allow method to release resources such as db connections ,file handle  threads
 * 10. @Value("${appConfiguration.paymentGateway}") for injecting data from configuration file into a class
 */
//@Component
public class OrderService {
    private final PaymentService paymentService;

    //@Autowired //telling spring to auto wire this class with its dependencies
    public OrderService(@Qualifier("stripe") PaymentService paymentService) {
        System.out.println("this is Order Service");
        this.paymentService = paymentService;
    }

    @PostConstruct //This is simply telling framework that execute this code after ordersevice class is initialize
                  //Useful since after initialization of an object then we can perform network connections ,database connections etc
    public void init(){
        System.out.println("orderService PostConstruct");
    }
   @PreDestroy  //This method allow us to release resources such as db connections ,file handle ,threads
   public void cleanUp(){
       System.out.println("Order Service CleanUP");
   }

    public void makeOrder( double amount){
        paymentService.processPayment(amount);
    }
}
