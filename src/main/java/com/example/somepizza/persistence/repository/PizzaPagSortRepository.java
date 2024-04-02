package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
    Page<PizzaEntity> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(Pageable pageable, String name, String description);
}
