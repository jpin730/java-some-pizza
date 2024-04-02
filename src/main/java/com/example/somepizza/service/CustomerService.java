package com.example.somepizza.service;

import com.example.somepizza.persistence.entity.CustomerEntity;
import com.example.somepizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<CustomerEntity> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<CustomerEntity> findByName(String name, Pageable pageable) {
        return customerRepository.findByName(name.toUpperCase(), pageable);
    }
}
