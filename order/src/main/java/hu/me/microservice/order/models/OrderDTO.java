package hu.me.microservice.order.models;

import java.time.LocalDate;

import hu.me.microservice.order.entity.Order;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private ProductDTO product;
    private Integer quantity;
    private Integer price;
    private String group;
    private OrderStatus status;
    private LocalDate createdAt;

    public static OrderDTO fromEntity(Order order, ProductDTO product) {
        return new OrderDTO(
            order.getId(),
            product,
            order.getQuantity(),
            order.getPrice(),
            order.getGroup(),
            OrderStatus.getStatus(order.getStatus()),
            order.getCreatedAt()
        );
    }
}
