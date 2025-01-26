package hu.me.microservice.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.me.microservice.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUserId(Long userId, Pageable page);
    List<Order> findAllByUserIdAndGroup(Long userId, String group);
}
