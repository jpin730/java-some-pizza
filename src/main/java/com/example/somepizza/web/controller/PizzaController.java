package com.example.somepizza.web.controller;

import com.example.somepizza.persistence.entity.PizzaEntity;
import com.example.somepizza.service.PizzaService;
import com.example.somepizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "4") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        if (!query.isEmpty()) {
            return ResponseEntity.ok(this.pizzaService.getByQuery(query, pageable));
        }
        return ResponseEntity.ok(this.pizzaService.getAll(pageable));
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

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        if (!this.pizzaService.existsById(dto.getId())) {
            return ResponseEntity.notFound().build();
        }
        this.pizzaService.updatePriceById(dto);
        return ResponseEntity.ok().build();
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
