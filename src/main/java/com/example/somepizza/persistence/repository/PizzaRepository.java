package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.PizzaEntity;
import com.example.somepizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    @Modifying
    @Query(value = "UPDATE pizza SET price = :#{#dto.price} WHERE id = :#{#dto.id}", nativeQuery = true)
    void updatePriceById(@Param("dto") UpdatePizzaPriceDto dto);
}
