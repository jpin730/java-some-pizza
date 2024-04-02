package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByOrderByIdAsc();

    List<OrderEntity> findAllByDateAfterOrderByIdAsc(LocalDateTime date);

    @Query(value = "SELECT * FROM pizza_order WHERE customer_id = :id", nativeQuery = true)
    List<OrderEntity> findByCustomerId(@Param("id") String id);
}
