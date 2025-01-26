package hu.me.microservice.order.services.impl;

import java.time.Duration;

import hu.me.microservice.order.models.OrderStatus;
import hu.me.microservice.order.repository.OrderRepository;

public class OrderStatusUpdaterThread extends Thread {
    private final OrderRepository orderRepository;
    private final Duration timeBetweenRuns;

    public OrderStatusUpdaterThread(OrderRepository orderRepository, Duration timeBetweenRuns) {
        super("OrderStatusUpdaterThread");

        this.orderRepository = orderRepository;
        this.timeBetweenRuns = timeBetweenRuns;
    }

    @Override
    public void run() {
        super.run();

        try {
                while(true) {
                    Thread.sleep(timeBetweenRuns.toMillis());
                    
                    System.out.println("Change Order Status to the next state");

                    int modifiedCount = this.orderRepository.updateStatus(OrderStatus.SHIPPING.valueOf(), OrderStatus.RECEIVED.valueOf());
                    System.out.printf("Modified %d entry from \"Shipping\" to \"Received\"\n", modifiedCount);
                    
                    modifiedCount = this.orderRepository.updateStatus(OrderStatus.PROCESSING.valueOf(), OrderStatus.SHIPPING.valueOf());
                    System.out.printf("Modified %d entry from \"Processing\" to \"Shipping\"\n", modifiedCount);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

}
