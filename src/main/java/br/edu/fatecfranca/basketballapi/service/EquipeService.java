package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.EquipeRequest;
import br.edu.fatecfranca.basketballapi.dto.EquipeResponse;
import br.edu.fatecfranca.basketballapi.model.Cidade;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.repository.EquipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository repository;

    public Page<EquipeResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(EquipeResponse::of);
    }

    public EquipeResponse findById(Long id) {
        return EquipeResponse.of(getById(id));
    }

    private Equipe getById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public EquipeResponse save(EquipeRequest request) {
        var equipe = Equipe.of(request);

        return EquipeResponse.of(repository.save(equipe));
    }

    @Transactional
    public EquipeResponse update(Long id, EquipeRequest request) {
        var equipe = getById(id);

        BeanUtils.copyProperties(request, equipe);
        equipe.setCidade(new Cidade(request.getCidadeId()));

        return EquipeResponse.of(repository.save(equipe));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar a equipe");
        }
    }
}
