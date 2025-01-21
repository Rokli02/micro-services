package hu.me.microservice.order.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hu.me.microservice.order.models.ProductDto;
import hu.me.microservice.order.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public List<ProductDto> getByIds(List<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByIds'");
    }

    @Override
    public boolean healthcheck() {
        try {
            String uri = "http://localhost:3002/healtcheck";
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
