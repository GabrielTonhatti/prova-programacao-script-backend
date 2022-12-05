package br.edu.fatecfranca.basketballapi.enums;

import br.edu.fatecfranca.basketballapi.dto.SelectResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum FuncaoTecnico {

    OFENSIVO("Ofensivo"),
    DEFENSIVO("Defensivo"),
    PREPARADOR_FISICO("Preparador Físico");

    private final String descricao;

    public static List<SelectResponse> of() {
        return Arrays.stream(FuncaoTecnico.values())
                .map(funcaoTecnico -> SelectResponse.of(funcaoTecnico.getDescricao(), funcaoTecnico.name()))
                .toList();
    }
}
