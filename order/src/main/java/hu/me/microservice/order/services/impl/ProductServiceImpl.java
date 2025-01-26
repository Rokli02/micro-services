package hu.me.microservice.order.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.ProductDTO;
import hu.me.microservice.order.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public Map<Long, ProductDTO> getByIds(List<Long> ids) {
        Map<Long, ProductDTO> productsMap = new HashMap<>();

        try {
            RestTemplate template = new RestTemplate();
            String uri = "http://localhost:3002/api/products/batch";

            @SuppressWarnings("rawtypes")
            ResponseEntity<List> response = template.postForEntity(uri, ids, List.class);

            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> products = response.getBody();

                if (products != null && products.size() != 0) {

                    for (int i = 0; i < products.size(); i++) {
                        ProductDTO prod = ProductDTO.fromMap(products.get(i));

                        productsMap.put(prod.getId(), prod);
                    }
                }
            }
        } catch (RuntimeException exc) {
            exc.printStackTrace();
            throw new BadOrderException("Couldn't get product details from \"Product\" service");
        }
        
        return productsMap;
    }

    @Override
    public boolean healthcheck() {
        try {
            String uri = "http://localhost:3002/api/healtcheck";
            RestTemplate template = new RestTemplate();
            
            @SuppressWarnings("unchecked")
            Map<String, String> response = template.getForObject(uri, Map.class);

            if (response != null) {
                String status = response.get("status");

                if (status != null && status.equals("Ok")) {
                    return true;
                }
            }
        } catch (RuntimeException exc) {
            System.err.println(exc.getMessage());
        }

        return false;
    }

}
