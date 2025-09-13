package com.morrisco.net.store.orderService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService{
    @Override
    public void processPayment(double amount) {
        System.out.println("Stripe");

        System.out.println("amount " + amount);
    }
}
