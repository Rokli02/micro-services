package hu.me.microservice.order.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Integer basePrice;
    private Float discount;
    private LocalDate createdAt;
    private Boolean isActive;
}
