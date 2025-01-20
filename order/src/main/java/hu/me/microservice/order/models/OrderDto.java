package hu.me.microservice.order.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private ProductDto product;
    private Long userId;
    private Integer quantity;
    private Integer price;
    private String group;
    private OrderStatus status;
    private LocalDate createdAt;
}
