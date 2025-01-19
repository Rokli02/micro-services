package hu.me.microservice.product.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestInfo {
    private Long id;
    private String name;
    private TestStatus status;

    public static TestInfo fromEntity(hu.me.microservice.product.entity.TestInfo entity) {
        return new TestInfo(entity.getId(), entity.getName(), TestStatus.getStatus(entity.getStatus()));
    }
}
