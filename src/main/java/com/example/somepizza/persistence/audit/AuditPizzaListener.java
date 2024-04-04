package com.example.somepizza.persistence.audit;

import com.example.somepizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditPizzaListener {
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("Pizza entity was created or updated: " + entity.toString());
    }

    @PreRemove
    public void onPreRemove(PizzaEntity entity) {
        System.out.println("Pizza entity will be removed: " + entity.toString());
    }
}
