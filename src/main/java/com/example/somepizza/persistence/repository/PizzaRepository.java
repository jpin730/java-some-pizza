package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPriceAsc();

    List<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCaseContaining(String name);
}
