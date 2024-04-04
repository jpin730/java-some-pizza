package com.example.somepizza.service;

import com.example.somepizza.persistence.entity.OrderEntity;
import com.example.somepizza.persistence.projection.OrderSummary;
import com.example.somepizza.persistence.repository.OrderRepository;
import com.example.somepizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfterOrderByIdAsc(today);
    }

    public List<OrderEntity> getByCustomerId(String id) {
        return this.orderRepository.findByCustomerId(id);
    }

    public OrderSummary getOrderSummaryById(Integer orderId) {
        return this.orderRepository.getOrderSummaryById(orderId);
    }

    @Transactional
    public Boolean saveRandomOrder(RandomOrderDto dto) {
        return this.orderRepository.saveRandomOrder(dto.getCustomerId(), dto.getMethod());
    }
}
