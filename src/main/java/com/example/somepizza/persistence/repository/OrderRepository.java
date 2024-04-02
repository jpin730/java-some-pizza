package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByOrderByIdAsc();

    List<OrderEntity> findAllByDateAfterOrderByIdAsc(LocalDateTime date);
}
