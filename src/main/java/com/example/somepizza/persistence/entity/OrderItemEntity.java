package com.example.somepizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    private Integer id;

    @Id
    private Integer orderId;

    @Column(nullable = false)
    private Integer pizzaId;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Integer quantity;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;
}
