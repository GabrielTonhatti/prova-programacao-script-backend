package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Equipe;
import br.edu.fatecfranca.basketballapi.model.Jogador;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JogadorResponse {

    private Long id;
    private String nome;
    private EquipeResponse equipe;

    public static JogadorResponse of(Jogador jogador) {
        return JogadorResponse
                .builder()
                .id(jogador.getId())
                .nome(jogador.getNome())
                .equipe(EquipeResponse.of(jogador.getEquipe()))
                .build();
    }

    public static Set<JogadorResponse> of(Set<Jogador> jogadores) {
        return jogadores.stream()
                .map(JogadorResponse::of)
                .collect(Collectors.toSet());
    }
}
