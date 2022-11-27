package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Jogador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogadorResponse {

    private Long id;
    private String nome;
    private Equipe equipe;

    public static JogadorResponse of(Jogador jogador) {
        return JogadorResponse
                .builder()
                .id(jogador.getId())
                .nome(jogador.getNome())
                .equipe(jogador.getEquipe())
                .build();
    }

}
