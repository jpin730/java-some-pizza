package com.example.somepizza.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getOrderId();

    String getCustomerName();

    LocalDateTime getOrderDate();

    Double getOrderTotal();

    String getPizzaNames();
}
