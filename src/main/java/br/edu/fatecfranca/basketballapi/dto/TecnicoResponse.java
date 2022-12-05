package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.enums.FuncaoTecnico;
import br.edu.fatecfranca.basketballapi.model.Tecnico;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private FuncaoTecnico funcaoCodigo;
    private String funcao;
    private String equipe;
    private Long equipeId;

    public static TecnicoResponse of(Tecnico tecnico) {
        return TecnicoResponse
                .builder()
                .id(tecnico.getId())
                .nome(tecnico.getNome())
                .equipeId(tecnico.getEquipe().getId())
                .equipe(tecnico.getEquipe().getNome())
                .funcao(tecnico.getFuncao().getDescricao())
                .funcaoCodigo(tecnico.getFuncao())
                .build();
    }

    public static Set<TecnicoResponse> of(List<Tecnico> tecnicos) {
        return tecnicos.stream()
                .map(TecnicoResponse::of)
                .collect(Collectors.toSet());
    }
}
