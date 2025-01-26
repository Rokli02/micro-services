package hu.me.microservice.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.NewOrderDTO;
import hu.me.microservice.order.models.OrderDTO;
import hu.me.microservice.order.services.OrderService;
import hu.me.microservice.order.services.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/order")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getByPage(
        @RequestHeader Map<String, String> headers,
        @RequestParam(defaultValue = "25") @Min(value = 1, message = "Az oldal 'size' minimum '1' kell hogy legyen") Integer size,
        @RequestParam(defaultValue = "0") @Min(value = 0, message = "Az oldal 'offset' minimum '0' kell hogy legyen") Integer offset
    ) {
        Long userId = this.userService.getUserId(headers);
        if (userId == null) {
            throw new BadOrderException("Anonim felhasználó nem kérheti le a korábbi vásárlásainak listáját");
        }

        Page<OrderDTO> ordersByPage = this.orderService.get(PageRequest.of(offset, size), userId);
        Map<String, Object> responseBody = new HashMap<String, Object>();
        responseBody.put("content", ordersByPage.getContent());
        responseBody.put("totalCount", ordersByPage.getTotalElements());
        responseBody.put("size", ordersByPage.getPageable().getPageSize());
        responseBody.put("offset", ordersByPage.getPageable().getOffset());

        return ResponseEntity.ok().body(responseBody);
    }
    
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveMany(
        @RequestHeader Map<String, String> headers,
        @RequestBody List<NewOrderDTO> newOrders
    ) {
        Long userId = this.userService.getUserId(headers);

        Map<String, Object> responseBody = new HashMap<String, Object>();

        if (newOrders.size() == 0) {
            responseBody.put("group", "");

            return ResponseEntity.ok().body(responseBody);
        }

        responseBody.put("group", this.orderService.saveMany(newOrders, userId));
        
        return ResponseEntity.ok().body(responseBody);
    }
    
    @GetMapping("finalize/{group}")
    public ResponseEntity<Map<String, Object>> finalizeOrder(
        @RequestHeader Map<String, String> headers,
        @PathVariable(required = true) @NotBlank String group
    ) {
        Long userId = this.userService.getUserId(headers);

        Map<String, Object> responseBody = new HashMap<String, Object>();
        
        responseBody.put("status", this.orderService.finalizeOrder(group, userId).toString());

        return ResponseEntity.ok().body(responseBody);
    }
    
    @GetMapping("group/{group}")
    public ResponseEntity<Object> getByGroup(
        @RequestHeader Map<String, String> headers,
        @PathVariable(required = true) @NotBlank String group,
        @RequestParam(defaultValue = "") String aggregated
    ) {
        Long userId = this.userService.getUserId(headers);
        Object responseBody;

        switch (aggregated) {
            case "true":
                responseBody = this.orderService.getByGroupAggregated(group, userId);
                break;
            default:
                responseBody = this.orderService.getByGroup(group, userId);
                break;
        }

        return ResponseEntity.ok().body(responseBody);
    }
    
}
