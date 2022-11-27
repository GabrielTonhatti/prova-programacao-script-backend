package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.TecnicoRequest;
import br.edu.fatecfranca.basketballapi.dto.TecnicoResponse;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Tecnico;
import br.edu.fatecfranca.basketballapi.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Page<TecnicoResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(TecnicoResponse::of);
    }

    public TecnicoResponse findById(Long id) {
        return TecnicoResponse.of(getById(id));
    }

    private Tecnico getById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public TecnicoResponse save(TecnicoRequest request) {
        var tecnico = Tecnico.of(request);

        return TecnicoResponse.of(repository.save(tecnico));
    }

    @Transactional
    public TecnicoResponse update(Long id, TecnicoRequest request) {
        var tecnico = getById(id);

        tecnico.setNome(request.getNome());
        tecnico.setEquipe(new Equipe(request.getEquipeId()));

        return TecnicoResponse.of(repository.save(tecnico));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar o técnico");
        }
    }

}
