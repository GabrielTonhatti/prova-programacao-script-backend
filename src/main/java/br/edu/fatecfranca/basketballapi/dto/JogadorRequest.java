package br.edu.fatecfranca.basketballapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogadorRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long equipeId;
}
