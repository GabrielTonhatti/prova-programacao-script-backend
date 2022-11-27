package br.edu.fatecfranca.basketballapi.controller;

import br.edu.fatecfranca.basketballapi.dto.EquipeRequest;
import br.edu.fatecfranca.basketballapi.dto.EquipeResponse;
import br.edu.fatecfranca.basketballapi.service.EquipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/equipes")
public class EquipeController {

    @Autowired
    private EquipeService service;

    @GetMapping
    public Page<EquipeResponse> findAll(Pageable page) {
        return service.findAll(page);
    }

    @GetMapping("{id}")
    public EquipeResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public EquipeResponse save(@RequestBody @Valid EquipeRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    public EquipeResponse update(@PathVariable Long id, @RequestBody @Valid EquipeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
