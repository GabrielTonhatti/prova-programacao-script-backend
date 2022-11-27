package br.edu.fatecfranca.basketballapi.dto;

import br.edu.fatecfranca.basketballapi.model.Cidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CidadeResponse {

    private Long id;
    private String nome;
    private String uf;

    public static CidadeResponse of(Cidade cidade) {
        return CidadeResponse
                .builder()
                .id(cidade.getId())
                .nome(cidade.getNome())
                .uf(cidade.getUf())
                .build();
    }

}
