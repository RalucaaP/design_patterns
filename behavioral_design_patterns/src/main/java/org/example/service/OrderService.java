package org.example.service;

import org.example.Order;
import org.example.command.PlaceOrderCommand;
import org.example.handler.InventoryCheckHandler;
import org.example.handler.PaymentValidationHandler;
import org.example.notification.NotificationService;
import org.example.notification.Observer;
import org.example.payment.CreditCardPayment;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public String processOrder(Order order) {
        InventoryCheckHandler inventoryHandler = new InventoryCheckHandler();
        PaymentValidationHandler paymentHandler = new PaymentValidationHandler();
        inventoryHandler.setNext(paymentHandler);

        inventoryHandler.validate(order);

        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(order);
        placeOrderCommand.execute();

        orderRepository.save(order);

        CreditCardPayment paymentStrategy = new CreditCardPayment();
        paymentStrategy.pay(order.getTotalAmount());

        NotificationService notificationService = new NotificationService();
        Observer emailObserver = message -> System.out.println("Email notification: " + message);
        Observer smsObserver = message -> System.out.println("SMS notification: " + message);
        notificationService.addObserver(emailObserver);
        notificationService.addObserver(smsObserver);

        order.updateStatus("Shipped");
        notificationService.notifyObservers("Order " + order.getId() + " has been shipped.");

        return "Order placed successfully with ID: " + order.getId();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
