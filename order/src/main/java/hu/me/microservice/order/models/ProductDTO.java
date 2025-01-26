package hu.me.microservice.order.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    public static ProductDTO fromMap(Map<String, Object> map) {
        LocalDate.now();
        return new ProductDTO(
            Long.valueOf((Integer) map.get("id")),
            (String) map.get("name"),
            (String) map.get("description"),
            (Integer) map.get("basePrice"),
            ((Double) map.get("discount")).floatValue(),
            LocalDate.parse((String) map.get("createdAt"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss")),
            (Boolean) map.get("isActive")
        );
    }
}
