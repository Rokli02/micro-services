package hu.me.microservice.order.services;

import java.util.List;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.AggregatedOrderDto;
import hu.me.microservice.order.models.NewOrderDto;
import hu.me.microservice.order.models.OrderDto;
import hu.me.microservice.order.models.PageDto;

public interface OrderService {
    int saveMany(List<NewOrderDto> orders, Long userId) throws BadOrderException;
    int finalizeOrder(String group, Long userId) throws BadOrderException;
    List<OrderDto> get(PageDto page, Long userId);
    List<OrderDto> getByGroup(String group, Long userId);
    AggregatedOrderDto getByGroupAggregated(String group, Long userId);
}