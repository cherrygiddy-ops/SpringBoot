package com.morrisco.net.store.orderService;

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
 */
//@Component
public class OrderService {
    private final PaymentService paymentService;

    @Autowired //telling spring to auto wire this class with its dependencies
    public OrderService(@Qualifier("stripe") PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public void makeOrder( double amount){
        paymentService.processPayment(amount);
    }
}
