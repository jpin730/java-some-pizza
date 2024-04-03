package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.OrderEntity;
import com.example.somepizza.persistence.projection.OrderSummary;
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

    @Query(value =
            "SELECT " +
                    "o.id AS orderId, " +
                    "c.`name` AS customerName, " +
                    "o.`date` AS orderDate, " +
                    "o.total AS orderTotal, " +
                    "GROUP_CONCAT(p. `name`) AS pizzaNames " +
                    "FROM pizza_order o " +
                    "INNER JOIN customer c ON o.customer_id = c.id " +
                    "INNER JOIN order_item i ON o.id = i.order_id " +
                    "INNER JOIN pizza p ON i.pizza_id = p.id " +
                    "WHERE o.id = :orderId " +
                    "GROUP BY o.id;", nativeQuery = true)
    OrderSummary getOrderSummaryById(@Param("orderId") Integer orderId);
}
