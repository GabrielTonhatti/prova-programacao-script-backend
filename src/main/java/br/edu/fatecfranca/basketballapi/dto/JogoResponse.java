package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Jogo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.edu.fatecfranca.basketballapi.utils.DateUtils.localDateTimeToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogoResponse {

    private Long id;
    private String data;
    private Integer pontosCasa;
    private Integer pontosVisitante;
    private EquipeResponse equipeCasa;
    private EquipeResponse equipeVisitante;

    public static JogoResponse of(Jogo jogo) {
        return JogoResponse
                .builder()
                .id(jogo.getId())
                .pontosCasa(jogo.getPontosCasa())
                .pontosVisitante(jogo.getPontosVisitante())
                .equipeCasa(EquipeResponse.of(jogo.getEquipeCasa()))
                .equipeVisitante(EquipeResponse.of(jogo.getEquipeVisitante()))
                .data(localDateTimeToString(jogo.getData(), "dd/MM/yyyy HH:mm:ss"))
                .build();
    }

}
