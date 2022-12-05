package br.edu.fatecfranca.basketballapi.service;

import br.edu.fatecfranca.basketballapi.dto.TecnicoRequest;
import br.edu.fatecfranca.basketballapi.dto.TecnicoResponse;
import br.edu.fatecfranca.basketballapi.handler.ErrorException;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Tecnico;
import br.edu.fatecfranca.basketballapi.repository.TecnicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private EquipeService equipeService;

    public Page<TecnicoResponse> findAll(Pageable page) {
        return repository.findAll(page)
                .map(TecnicoResponse::of);
    }

    public TecnicoResponse findById(Long id) {
        return TecnicoResponse.of(getById(id));
    }

    public Tecnico getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ErrorException("Técnico não encontrado"));
    }

    @Transactional
    public TecnicoResponse save(TecnicoRequest request) {
        var equipe = equipeService.getById(request.getEquipeId());
        var tecnico = Tecnico.of(request, equipe);
        validarQuantidadeTecnicos(equipe, request, tecnico);

        return TecnicoResponse.of(repository.save(tecnico));
    }

    private void validarQuantidadeTecnicos(Equipe equipe, TecnicoRequest request, Tecnico tecnico) {
        var tecnicoId = Optional.ofNullable(tecnico).map(Tecnico::getId).orElse(0L);
        var tecnicoDefensivo = equipe.getTecnicos()
                .stream()
                .filter(tecnicoAtual -> tecnicoAtual.isDefensivo() && !Objects.equals(tecnicoAtual.getId(), tecnicoId))
                .findFirst()
                .orElse(null);
        var tecnicoOfensivo = equipe.getTecnicos()
                .stream()
                .filter(tecnicoAtual -> tecnicoAtual.isOfensivo() && !Objects.equals(tecnicoAtual.getId(), tecnicoId))
                .findFirst()
                .orElse(null);
        var tecniisPreparadorFisico = equipe.getTecnicos()
                .stream()
                .filter(tecnicoAtual -> tecnicoAtual.isPreparadorFisico() && !Objects.equals(tecnicoAtual.getId(), tecnicoId))
                .findFirst()
                .orElse(null);

        if (request.isDefensivo() && tecnicoDefensivo != null) {
            throw new ErrorException("Uma equipe só pode ter um técnico defensivo");
        } else if (request.isOfensivo() && tecnicoOfensivo != null) {
            throw new ErrorException("Uma equipe só pode ter um técnico ofensivo");
        } else if (request.isPreparadorFisico() && tecniisPreparadorFisico != null) {
            throw new ErrorException("Uma equipe só pode ter um técnico preparador físico");
        }
    }

    @Transactional
    public TecnicoResponse update(Long id, TecnicoRequest request) {
        var tecnico = getById(id);
        var equipe = equipeService.getById(request.getEquipeId());
        validarQuantidadeTecnicos(equipe, request, tecnico);

        BeanUtils.copyProperties(request, tecnico, "id");

        return TecnicoResponse.of(repository.save(tecnico));
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ErrorException("Não foi possível deletar o técnico");
        }
    }

}
