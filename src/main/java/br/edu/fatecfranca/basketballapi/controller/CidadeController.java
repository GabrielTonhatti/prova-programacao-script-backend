package br.edu.fatecfranca.basketballapi.controller;

import br.edu.fatecfranca.basketballapi.dto.CidadeRequest;
import br.edu.fatecfranca.basketballapi.dto.CidadeResponse;
import br.edu.fatecfranca.basketballapi.dto.SelectResponse;
import br.edu.fatecfranca.basketballapi.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @GetMapping
    public Page<CidadeResponse> findAll(Pageable page) {
        return service.findAll(page);
    }

    @GetMapping("select")
    public List<SelectResponse> findAllSelect() {
        return service.findAllSelect();
    }

    @GetMapping("{id}")
    public CidadeResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CidadeResponse save(@RequestBody @Valid CidadeRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    public CidadeResponse update(@PathVariable Long id, @RequestBody @Valid CidadeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
