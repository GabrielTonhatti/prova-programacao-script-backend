package br.edu.fatecfranca.basketballapi.controller;

import br.edu.fatecfranca.basketballapi.dto.TecnicoRequest;
import br.edu.fatecfranca.basketballapi.dto.TecnicoResponse;
import br.edu.fatecfranca.basketballapi.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping
    public Page<TecnicoResponse> findAll(Pageable page) {
        return service.findAll(page);
    }

    @GetMapping("{id}")
    public TecnicoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TecnicoResponse save(@RequestBody @Valid TecnicoRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    public TecnicoResponse update(@PathVariable Long id, @RequestBody @Valid TecnicoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
