package com.example.somepizza.service;

import com.example.somepizza.persistence.entity.PizzaEntity;
import com.example.somepizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAllAvailable() {
        return pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
    }

    public List<PizzaEntity> getByName(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCaseContaining(name);
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
}
