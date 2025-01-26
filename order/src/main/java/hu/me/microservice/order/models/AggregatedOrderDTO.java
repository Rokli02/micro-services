package hu.me.microservice.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedOrderDTO {
    private Integer price;
    private String group;
    private Integer quantity;
    private OrderStatus status;

    public void incrementPrice(int price) {
        this.price += price;
    }

    public void incrementQuantity(int quantity) {
        this.quantity += quantity;
    }
}
