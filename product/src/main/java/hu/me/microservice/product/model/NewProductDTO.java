package hu.me.microservice.product.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDTO {
    private String name;
    private String description;
    private int basePrice;
    private float discount = 0;
}
