package br.edu.fatecfranca.basketballapi.controller;

import br.edu.fatecfranca.basketballapi.dto.JogoRequest;
import br.edu.fatecfranca.basketballapi.dto.JogoResponse;
import br.edu.fatecfranca.basketballapi.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/jogos")
public class JogoController {

    @Autowired
    private JogoService service;

    @GetMapping
    public Page<JogoResponse> findAll(Pageable page) {
        return service.findAll(page);
    }

    @GetMapping("{id}")
    public JogoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public JogoResponse save(@RequestBody @Valid JogoRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    public JogoResponse update(@PathVariable Long id, @RequestBody @Valid JogoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
