package hu.me.microservice.order.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.AggregatedOrderDTO;
import hu.me.microservice.order.models.NewOrderDTO;
import hu.me.microservice.order.models.OrderDTO;
import hu.me.microservice.order.models.OrderStatus;

public interface OrderService {
    String saveMany(List<NewOrderDTO> orders, Long userId) throws BadOrderException;
    OrderStatus finalizeOrder(String group, Long userId) throws BadOrderException;
    Page<OrderDTO> get(Pageable page, Long userId);
    List<OrderDTO> getByGroup(String group, Long userId);
    AggregatedOrderDTO getByGroupAggregated(String group, Long userId);
}