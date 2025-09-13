package com.morrisco.net.store.orderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService{
    @Value("${stripe.apiUrl}")
    private String apiurl;
    @Value("${stripe.TimeOut}")
    private int timeOut;
    @Value("${stripe.isEnabled}")
    private boolean  isEnabled;
    @Value("${stripe.currencies}")
    private List<String> currencies;

    @Override
    public void processPayment(double amount) {
        System.out.println(apiurl);
        System.out.println(timeOut);
        System.out.println(isEnabled);
        System.out.println(currencies);
    }
}
