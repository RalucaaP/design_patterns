package org.example.handler;

import org.example.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentValidationHandler extends OrderValidationHandler {

    @Override
    public void validate(Order order) {
        boolean isValidPayment = validatePayment(order);
        if (!isValidPayment) {
            log.error("Payment validation failed for order ID: {}", order.getId());
            throw new RuntimeException("Payment validation failed for order ID: " + order.getId());
        }
        log.info("Payment validation passed for order ID: {}", order.getId());
        super.validate(order);
    }

    private boolean validatePayment(Order order) {
        if (order.getTotalAmount() <= 0) {
            log.warn("Invalid payment amount for order ID: {}", order.getId());
            return false;
        }
        return true;
    }
}
