package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.JogoRequest;
import br.edu.fatecfranca.basketballapi.dto.JogoResponse;
import br.edu.fatecfranca.basketballapi.handler.ErrorException;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Jogo;
import br.edu.fatecfranca.basketballapi.repository.JogoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;
    @Autowired
    private EquipeService equipeService;

    public Page<JogoResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(JogoResponse::of);
    }

    public JogoResponse findById(Long id) {
        return JogoResponse.of(getById(id));
    }

    private Jogo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ErrorException("Jogo não encontrado"));
    }

    @Transactional
    public JogoResponse save(JogoRequest request) {
        var equipeCasa = equipeService.getById(request.getEquipeCasaId());
        var equipeVisitante = equipeService.getById(request.getEquipeVisitanteId());
        validarEquipes(equipeCasa, equipeVisitante);

        var jogo = Jogo.of(request, equipeCasa, equipeVisitante);

        return JogoResponse.of(repository.save(jogo));
    }

    private void validarEquipes(Equipe equipeCasa, Equipe equipeVisitante) {
        if (equipeCasa.getJogadores().size() < 9) {
            throw new ErrorException("A equipe da casa deve ter no mínimo 9 jogadores");
        }

        if (equipeVisitante.getJogadores().size() < 9) {
            throw new ErrorException("A equipe visitante deve ter no mínimo 9 jogadores");
        }

        if (equipeCasa.getTecnicos().size() < 3) {
            throw new ErrorException("A equipe da casa deve ter no mínimo 3 técnicos");
        }

        if (equipeVisitante.getTecnicos().size() < 3) {
            throw new ErrorException("A equipe visitante deve ter no mínimo 3 técnicos");
        }
    }

    @Transactional
    public JogoResponse update(Long id, JogoRequest request) {
        var jogo = getById(id);

        BeanUtils.copyProperties(request, jogo);
        jogo.setEquipeCasa(new Equipe(request.getEquipeCasaId()));
        jogo.setEquipeVisitante(new Equipe(request.getEquipeVisitanteId()));

        return JogoResponse.of(repository.save(jogo));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ErrorException("Jogo não encontrado");
        }
    }

}
