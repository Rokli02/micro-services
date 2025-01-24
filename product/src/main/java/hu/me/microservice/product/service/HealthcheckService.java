package hu.me.microservice.product.services;

import java.util.List;

import hu.me.microservice.product.models.TestInfo;

public interface HealthcheckService {
    List<TestInfo> getAll();
}
