package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Equipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeResponse {

    private Long id;
    private String nome;
    private CidadeResponse cidade;

    public static EquipeResponse of(Equipe equipe) {
        return EquipeResponse
                .builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .cidade(CidadeResponse.of(equipe.getCidade()))
                .build();
    }
}
