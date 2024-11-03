package org.example.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardPayment implements PaymentStrategy {
    private static final Logger log = LoggerFactory.getLogger(CreditCardPayment.class);

    @Override
    public void pay(double amount) {
        log.info("Paid ${} with Credit Card.", amount);
    }
}
