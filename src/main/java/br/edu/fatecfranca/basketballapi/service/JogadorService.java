package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.JogadorRequest;
import br.edu.fatecfranca.basketballapi.dto.JogadorResponse;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Jogador;
import br.edu.fatecfranca.basketballapi.repository.JogadorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    public Page<JogadorResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(JogadorResponse::of);
    }

    private Jogador getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
    }

    public JogadorResponse findById(Long id) {
        return JogadorResponse.of(getById(id));
    }

    @Transactional
    public JogadorResponse save(JogadorRequest request) {
        var jogador = Jogador.of(request);

        return JogadorResponse.of(repository.save(jogador));
    }

    @Transactional
    public JogadorResponse update(Long id, JogadorRequest request) {
        var jogador = getById(id);

        BeanUtils.copyProperties(request, jogador, "id");
        jogador.setEquipe(new Equipe(request.getEquipeId()));

        return JogadorResponse.of(repository.save(jogador));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Jogador não encontrado");
        }
    }
}
