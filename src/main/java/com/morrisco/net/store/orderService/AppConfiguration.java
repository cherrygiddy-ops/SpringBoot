package com.morrisco.net.store.orderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class AppConfiguration {
    @Value("${appConfiguration.paymentGateway}")
    private String paymentService;
    @Bean
    public PaymentService stripe(){
        return new StripePaymentService();
    }

    public PaymentService paypal(){
        return new PaypalPaymentService();
    }
    @Bean
    //@Scope("prototype")
    public OrderService orderService(){
        if (paymentService.equals("stripe"))
            return new OrderService(stripe());
        else
            return new OrderService(paypal());
    }
}
