package hu.me.microservice.order.services;

import java.util.List;
import java.util.Map;

import hu.me.microservice.order.models.ProductDTO;

public interface ProductService {
    Map<Long, ProductDTO> getByIds(List<Long> ids);
    boolean healthcheck();
}
