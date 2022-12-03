package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.JogadorRequest;
import br.edu.fatecfranca.basketballapi.dto.JogadorResponse;
import br.edu.fatecfranca.basketballapi.handler.ErrorException;
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
    @Autowired
    private EquipeService equipeService;

    public Page<JogadorResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(JogadorResponse::of);
    }

    public Jogador getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ErrorException("Jogador não encontrado"));
    }

    public JogadorResponse findById(Long id) {
        return JogadorResponse.of(getById(id));
    }

    @Transactional
    public JogadorResponse save(JogadorRequest request) {
        var equipe = equipeService.getById(request.getEquipeId());
        validarQuantidadeJogadores(equipe);

        var jogador = Jogador.of(request, equipe);

        return JogadorResponse.of(repository.save(jogador));
    }

    private void validarQuantidadeJogadores(Equipe equipe) {
        if (equipe.getJogadores().size() >= 12) {
            throw new ErrorException("Uma equipe só pode ter no máximo 12 jogadores");
        }
    }

    @Transactional
    public JogadorResponse update(Long id, JogadorRequest request) {
        var equipe = equipeService.getById(request.getEquipeId());
        validarQuantidadeJogadores(equipe);
        var jogador = getById(id);

        BeanUtils.copyProperties(request, jogador, "id");
        jogador.setEquipe(equipe);

        return JogadorResponse.of(repository.save(jogador));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ErrorException("Jogador não encontrado");
        }
    }
}
