package com.example.somepizza.web.controller;

import com.example.somepizza.persistence.entity.OrderEntity;
import com.example.somepizza.persistence.projection.OrderSummary;
import com.example.somepizza.service.OrderService;
import com.example.somepizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(
            @RequestParam(required = false, defaultValue = "") String customer
    ) {
        if (!customer.isEmpty()) {
            return ResponseEntity.ok(this.orderService.getByCustomerId(customer));
        }
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getOrderSummaryById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(this.orderService.getOrderSummaryById(orderId));
    }

    @PostMapping("/random")
    public ResponseEntity<Void> saveRandomOrder(@RequestBody RandomOrderDto dto) {
        Boolean result = this.orderService.saveRandomOrder(dto);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
