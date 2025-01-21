package hu.me.microservice.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.me.microservice.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
