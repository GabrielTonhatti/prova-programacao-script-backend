package br.edu.fatecfranca.basketballapi.controller;

import br.edu.fatecfranca.basketballapi.dto.JogadorRequest;
import br.edu.fatecfranca.basketballapi.dto.JogadorResponse;
import br.edu.fatecfranca.basketballapi.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService service;

    @GetMapping
    public Page<JogadorResponse> findAll(Pageable page) {
        return service.findAll(page);
    }

    @GetMapping("{id}")
    public JogadorResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public JogadorResponse save(@RequestBody @Valid JogadorRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    public JogadorResponse update(@PathVariable Long id, @RequestBody @Valid JogadorRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
