package org.example.command;

import org.example.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlaceOrderCommand implements OrderCommand {
    private final Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.updateStatus("Placed");
        log.info("Order placed successfully: {}", order.getId());
    }
}
