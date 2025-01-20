package hu.me.microservice.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedOrderDto {
    private Integer price;
    private String group;
    private Integer quantity;
    private OrderStatus status;
}
