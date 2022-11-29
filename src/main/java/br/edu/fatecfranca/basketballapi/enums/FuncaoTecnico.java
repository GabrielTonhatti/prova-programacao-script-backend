package br.edu.fatecfranca.basketballapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FuncaoTecnico {

    OFENSIVO("Ofensivo"),
    DEFENSIVO("Defensivo"),
    PREPARADOR_FISICO("Preparador Físico");

    private final String descricao;
}
