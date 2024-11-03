package org.example.handler;

import lombok.Setter;
import org.example.Order;

@Setter
public abstract class OrderValidationHandler {
    protected OrderValidationHandler next;

    public void validate(Order order) {
        if (next != null) {
            next.validate(order);
        }
    }
}
