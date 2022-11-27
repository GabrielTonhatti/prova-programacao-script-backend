package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.CidadeRequest;
import br.edu.fatecfranca.basketballapi.dto.CidadeResponse;
import br.edu.fatecfranca.basketballapi.model.Cidade;
import br.edu.fatecfranca.basketballapi.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Page<CidadeResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(CidadeResponse::of);
    }

    public CidadeResponse findById(Long id) {
        return CidadeResponse.of(getById(id));
    }

    private Cidade getById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public CidadeResponse save(CidadeRequest request) {
        var cidade = Cidade.of(request);

        return CidadeResponse.of(repository.save(cidade));
    }

    @Transactional
    public CidadeResponse update(Long id, CidadeRequest request) {
        var cidade = getById(id);

        BeanUtils.copyProperties(request, cidade);

        return CidadeResponse.of(repository.save(cidade));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar a cidade");
        }
    }

}
