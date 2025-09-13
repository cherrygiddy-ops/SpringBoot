package com.morrisco.net.store.orderService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ExpensiveResource {
    public ExpensiveResource() {
        System.out.println("expensive");
    }
}
