package org.example.handler;

import org.example.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InventoryCheckHandler extends OrderValidationHandler {

    @Override
    public void validate(Order order) {
        boolean isInStock = checkInventory(order);
        if (!isInStock) {
            log.error("Items not in stock for order ID: {}", order.getId());
            throw new RuntimeException("Items not in stock for order ID: " + order.getId());
        }
        log.info("Inventory check passed for order ID: {}", order.getId());
        super.validate(order);
    }

    private boolean checkInventory(Order order) {
        return true;
    }
}
