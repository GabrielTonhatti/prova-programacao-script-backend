package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Tecnico;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TecnicoResponse {

    private Long id;
    private String nome;
    private EquipeResponse equipe;
    private String funcao;

    public static TecnicoResponse of(Tecnico tecnico) {
        return TecnicoResponse
                .builder()
                .id(tecnico.getId())
                .nome(tecnico.getNome())
                .equipe(Optional.ofNullable(tecnico.getEquipe()).map(EquipeResponse::of).orElse(null))
                .funcao(tecnico.getFuncao().getDescricao())
                .build();
    }

    public static Set<TecnicoResponse> of(Set<Tecnico> tecnicos) {
        return tecnicos.stream()
                .map(TecnicoResponse::of)
                .collect(Collectors.toSet());
    }
}
