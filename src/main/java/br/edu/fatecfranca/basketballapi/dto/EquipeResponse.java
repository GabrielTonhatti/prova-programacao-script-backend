package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Cidade;
import br.edu.fatecfranca.basketballapi.model.Equipe;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipeResponse {

    private Long id;
    private String nome;
    private CidadeResponse cidade;
    private Set<JogadorResponse> jogadores;
    private  Set<TecnicoResponse> tecnicos;

    public static EquipeResponse of(Equipe equipe) {
        return EquipeResponse
                .builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .cidade(Optional.ofNullable(equipe.getCidade()).map(CidadeResponse::of).orElse(null))
                .jogadores(Optional.ofNullable(equipe.getJogadores()).map(JogadorResponse::of).orElse(null))
                .tecnicos(Optional.ofNullable(equipe.getTecnicos()).map(TecnicoResponse::of).orElse(null))
                .build();
    }
}
