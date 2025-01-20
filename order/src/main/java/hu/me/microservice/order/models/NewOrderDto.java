package hu.me.microservice.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDto {
    private Long productId;
    private Integer quantity;
    private String group;
}
