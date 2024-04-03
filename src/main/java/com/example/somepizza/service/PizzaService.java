package com.example.somepizza.service;

import com.example.somepizza.persistence.entity.PizzaEntity;
import com.example.somepizza.persistence.repository.PizzaPagSortRepository;
import com.example.somepizza.persistence.repository.PizzaRepository;
import com.example.somepizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(Pageable pageable) {
        return pizzaPagSortRepository.findAll(pageable);
    }


    public Page<PizzaEntity> getByQuery(String query, Pageable pageable) {
        return pizzaPagSortRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(pageable, query, query);
    }

    public PizzaEntity getById(Integer id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(Integer id) {
        pizzaRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    @Transactional
    public void updatePriceById(UpdatePizzaPriceDto dto) {
        pizzaRepository.updatePriceById(dto);
    }
}
