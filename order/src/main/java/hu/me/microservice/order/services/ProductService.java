package hu.me.microservice.order.services;

import java.util.List;

import hu.me.microservice.order.models.ProductDto;

public interface ProductService {
    List<ProductDto> getByIds(List<Long> ids);
    boolean healthcheck();
}
