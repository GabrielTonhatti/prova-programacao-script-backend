package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.EquipeRequest;
import br.edu.fatecfranca.basketballapi.dto.EquipeResponse;
import br.edu.fatecfranca.basketballapi.enums.FuncaoTecnico;
import br.edu.fatecfranca.basketballapi.handler.ErrorException;
import br.edu.fatecfranca.basketballapi.model.Cidade;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Jogador;
import br.edu.fatecfranca.basketballapi.model.Tecnico;
import br.edu.fatecfranca.basketballapi.repository.EquipeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository repository;

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private TecnicoService tecnicoService;

    public Page<EquipeResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(EquipeResponse::of);
    }

    public EquipeResponse findById(Long id) {
        return EquipeResponse.of(getById(id));
    }

    private Equipe getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ErrorException("Equipe não encontrada"));
    }

    @Transactional
    public EquipeResponse save(EquipeRequest request) {
        var jogadores = validarQuantidadeJogadores(request);
        var tecnicos = validarQuantidadeTecnicos(request);
        var equipe = Equipe.of(request);

        repository.save(equipe);

        jogadores.forEach(jogador -> {
            jogador.setEquipe(equipe);

            jogadorService.save(jogador);
        });

        tecnicos.forEach(tecnico -> {
            tecnico.setEquipe(equipe);

            tecnicoService.save(tecnico);
        });

        return EquipeResponse.of(equipe);
    }

    private Set<Tecnico> validarQuantidadeTecnicos(EquipeRequest request) {
        if (request.getTecnicosIds().size() > 3) {
            throw new ErrorException("Uma equipe só pode ter no máximo 3 técnicos");
        }

        var tecnicos = request.getTecnicosIds()
                .stream()
                .map(tecnicoService::getById)
                .collect(Collectors.toSet());

        var tecnicosOfensivos = tecnicos
                .stream()
                .filter(tecnico -> tecnico.getFuncao() == FuncaoTecnico.OFENSIVO)
                .count();

        var tecnicosDefensivos = tecnicos
                .stream()
                .filter(tecnico -> tecnico.getFuncao() == FuncaoTecnico.DEFENSIVO)
                .count();

        var tecnicosPreparoFisico = tecnicos
                .stream()
                .filter(tecnico -> tecnico.getFuncao() == FuncaoTecnico.PREPARADOR_FISICO)
                .count();

        if (tecnicosOfensivos > 1) {
            throw new ErrorException("Uma equipe só pode ter no máximo 1 técnico ofensivo");
        } else if (tecnicosDefensivos > 1) {
            throw new ErrorException("Uma equipe só pode ter no máximo 1 técnico defensivo");
        } else if (tecnicosPreparoFisico > 1) {
            throw new ErrorException("Uma equipe só pode ter no máximo 1 técnico preparador físico");
        }

        return tecnicos;
    }

    private Set<Jogador> validarQuantidadeJogadores(EquipeRequest request) {
        if (request.getJogadoresIds().size() < 9) {
            throw new ErrorException("Equipe deve ter no mínimo 9 jogadores");
        } else if (request.getJogadoresIds().size() > 12) {
            throw new ErrorException("Equipe deve ter no máximo 12 jogadores");
        }

        return request.getJogadoresIds()
                .stream()
                .map(jogadorService::getById)
                .collect(Collectors.toSet());
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
            throw new ErrorException("Não foi possível deletar a equipe");
        }
    }
}
