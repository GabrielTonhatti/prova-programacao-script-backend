package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoResponse {

    private Long id;
    private String nome;
    private EquipeResponse equipe;

    public static TecnicoResponse of(Tecnico tecnico) {
        return TecnicoResponse
                .builder()
                .id(tecnico.getId())
                .nome(tecnico.getNome())
                .equipe(Optional.ofNullable(tecnico.getEquipe()).map(EquipeResponse::of).orElse(null))
                .build();
    }
}
