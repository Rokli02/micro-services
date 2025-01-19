package hu.me.microservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.me.microservice.product.models.TestInfo;
import hu.me.microservice.product.services.HealthcheckService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/healtcheck")
public class HealthcheckController {
    private final HealthcheckService healthcheckService;

    public HealthcheckController(HealthcheckService healthcheckService) {
        this.healthcheckService = healthcheckService;
    }


    @GetMapping("")
    public Map<String, String> getHealthCheck() {
        Map<String, String> status = new HashMap<String, String>();

        status.put("status", "Ok");

        return status;
    }


    @GetMapping("test-info")
    public List<TestInfo> getMethodName() {
        return this.healthcheckService.getAll();
    }
    
}
