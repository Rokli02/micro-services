package hu.me.microservice.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/healtcheck")
public class HealthcheckController {
    @GetMapping("")
    public Map<String, String> getHealthCheck() {
        Map<String, String> status = new HashMap<String, String>();

        status.put("status", "Ok");

        return status;
    } 
}
