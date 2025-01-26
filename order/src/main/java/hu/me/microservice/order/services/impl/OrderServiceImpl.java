package hu.me.microservice.order.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hu.me.microservice.order.entity.Order;
import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.AggregatedOrderDTO;
import hu.me.microservice.order.models.NewOrderDTO;
import hu.me.microservice.order.models.OrderDTO;
import hu.me.microservice.order.models.OrderStatus;
import hu.me.microservice.order.models.ProductDTO;
import hu.me.microservice.order.repository.OrderRepository;
import hu.me.microservice.order.services.OrderService;
import hu.me.microservice.order.services.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(ProductService productService, OrderRepository orderRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;

        if (this.productService.healthcheck()) {
            System.out.println("A \"Product\" szervíz elérhető");
        } else {
            System.out.println("A \"Product\" szervíz még nem érhető el");
        }

        //TODO: Egy zöld szálat elindítani, ami 5 percenként updateli a megfelelő státuszú rendeléseket
    }

    @Override
    public String saveMany(List<NewOrderDTO> newOrders, Long userId) throws BadOrderException {
        List<Long> productIds = newOrders.stream().map((no) -> no.getProductId()).collect(Collectors.toList());
        Map<Long, ProductDTO> productsMap = this.productService.getByIds(productIds);
        long _userId = userId == null ? 1L : userId;
        String orderGroup = String.format("%s.%d-%d", String.valueOf(System.currentTimeMillis()), _userId, productIds.size());

        List<Order> orders = newOrders.stream().map((o) -> {
            ProductDTO product = productsMap.get(o.getProductId());
            if (product == null) {
                return null;
            }

            float rawOrderPrice = o.getQuantity().floatValue() * product.getBasePrice().floatValue() * (1f - Math.max(0f, Math.min(1f, product.getDiscount().floatValue())));
            int orderPrice = Math.round(rawOrderPrice);

            Order order = new Order(
                null,
                o.getProductId(),
                _userId,
                o.getQuantity(),
                orderPrice,
                o.getGroup() != null ? o.getGroup() : orderGroup,
                OrderStatus.CREATED.valueOf(),
                null
            );

            return order;
        }).filter((o) -> o != null).collect(Collectors.toList());
        
        orderRepository.saveAll(orders);

        return orderGroup;
    }

    @Override
    public OrderStatus finalizeOrder(String group, Long userId) throws BadOrderException {
        // TODO: Adatbázis művelet a két paraméter felhasználásával -> update status

        return OrderStatus.UNKNOWN;
    }

    @Override
    public Page<OrderDTO> get(Pageable page, Long userId) {
        Page<Order> orders = this.orderRepository.findAllByUserId(userId, page);
        List<Long> productIds = orders.stream().map((o) -> o.getProductId()).collect(Collectors.toList());
        Map<Long, ProductDTO> productsMap = this.productService.getByIds(productIds);

        return new PageImpl<>(
            orders.map((o) -> OrderDTO.fromEntity(o, productsMap.get(o.getProductId()))).toList(),
            orders.getPageable(),
            orders.getTotalElements()
        );
    }

    @Override
    public List<OrderDTO> getByGroup(String group, Long userId) {
        List<Order> orders = this.orderRepository.findAllByUserIdAndGroup(userId, group);

        if (orders.size() == 0) {
            return new ArrayList<OrderDTO>();
        }

        List<Long> productIds = orders.stream().map((o) -> o.getProductId()).collect(Collectors.toList());
        Map<Long, ProductDTO> productsMap = this.productService.getByIds(productIds);

        return orders.stream().map((o) -> OrderDTO.fromEntity(o, productsMap.get(o.getProductId()))).toList();
    }

    @Override
    public AggregatedOrderDTO getByGroupAggregated(String group, Long userId) {
        AggregatedOrderDTO aggregatedOrder = new AggregatedOrderDTO(0, group, 0, OrderStatus.UNKNOWN);
        List<Order> orders = this.orderRepository.findAllByUserIdAndGroup(userId, group);

        if (orders.size() == 0) {
            return aggregatedOrder;
        }

        
        aggregatedOrder.setStatus(OrderStatus.getStatus(orders.get(0).getStatus()));

        orders.stream().forEach((o) -> {
            aggregatedOrder.incrementPrice(o.getPrice());
            aggregatedOrder.incrementQuantity(o.getQuantity());
        });

        return aggregatedOrder;
    }

}
