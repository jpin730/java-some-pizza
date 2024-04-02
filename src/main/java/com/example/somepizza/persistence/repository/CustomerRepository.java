package com.example.somepizza.persistence.repository;

import com.example.somepizza.persistence.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListPagingAndSortingRepository<CustomerEntity, String> {
    @Query("SELECT c FROM CustomerEntity c WHERE UPPER(c.name) LIKE %:name%")
    Page<CustomerEntity> findByName(@Param("name") String name, Pageable pageable);
}
