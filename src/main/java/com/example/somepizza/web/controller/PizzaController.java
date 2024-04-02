package com.example.somepizza.web.controller;

import com.example.somepizza.persistence.entity.PizzaEntity;
import com.example.somepizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(
            @RequestParam(required = false, defaultValue = "") String query
    ) {
        if (!query.isEmpty()) {
            return ResponseEntity.ok(this.pizzaService.getByQuery(query));
        }
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Integer id) {
        PizzaEntity pizza = this.pizzaService.getById(id);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pizza);
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza) {
        if (pizza.getId() != null && this.pizzaService.existsById(pizza.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.pizzaService.save(pizza));
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!this.pizzaService.existsById(pizza.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.pizzaService.save(pizza));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (!this.pizzaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.pizzaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
