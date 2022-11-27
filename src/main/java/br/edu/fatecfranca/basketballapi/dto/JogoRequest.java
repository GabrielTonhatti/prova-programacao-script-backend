package br.edu.fatecfranca.basketballapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogoRequest {

    @NotNull
    private Long equipeCasaId;

    @NotNull
    private Long equipeVisitanteId;

    @NotNull
    private Integer pontosCasa;

    @NotNull
    private Integer pontosVisitante;
}
