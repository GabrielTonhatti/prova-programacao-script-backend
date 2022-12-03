package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.enums.FuncaoTecnico;
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
public class TecnicoRequest {

    @NotBlank
    private String nome;

    @NotNull
    private FuncaoTecnico funcao;

    @NotNull
    private Long equipeId;

    public boolean isDefensivo() {
        return this.funcao.equals(FuncaoTecnico.DEFENSIVO);
    }

    public boolean isOfensivo() {
        return this.funcao.equals(FuncaoTecnico.OFENSIVO);
    }

    public boolean isPreparadorFisico() {
        return this.funcao.equals(FuncaoTecnico.PREPARADOR_FISICO);
    }
}
