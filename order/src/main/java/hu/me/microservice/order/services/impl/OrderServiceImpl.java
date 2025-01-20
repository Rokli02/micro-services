package hu.me.microservice.order.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.AggregatedOrderDto;
import hu.me.microservice.order.models.NewOrderDto;
import hu.me.microservice.order.models.OrderDto;
import hu.me.microservice.order.models.OrderStatus;
import hu.me.microservice.order.models.PageDto;
import hu.me.microservice.order.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public int saveMany(List<NewOrderDto> orders, Long userId) throws BadOrderException {
        List<Long> productIds = orders.stream().map((no) -> no.getProductId()).collect(Collectors.toList());

        // TODO: A termék id-k alapján lekérdezni a Product szervíztől a megfelelő termékeket
        
        return productIds.size();
    }

    @Override
    public int finalizeOrder(String group, Long userId) throws BadOrderException {
        // TODO: Adatbázis művelet a két paraméter felhasználásával -> update status

        return 0;
    }

    @Override
    public List<OrderDto> get(PageDto page, Long userId) {
        // TODO: Adatbázis művelet a két paraméter felhasználásával -> get all by page and userId
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<OrderDto> getByGroup(String group, Long userId) {
        // TODO: Adatbázis művelet a két paraméter felhasználásával -> get all by group and userId
        throw new UnsupportedOperationException("Unimplemented method 'getByGroup'");
    }

    @Override
    public AggregatedOrderDto getByGroupAggregated(String group, Long userId) {
        // TODO: Adatbázis művelet a két paraméter felhasználásával -> get all by group and userId

        // TODO: A visszakapott eredményt feldolgozni
        return new AggregatedOrderDto(0, "unknown", 0, OrderStatus.UNKNOWN);
    }

}
