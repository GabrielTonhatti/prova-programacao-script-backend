package br.edu.fatecfranca.basketballapi.model;

import br.edu.fatecfranca.basketballapi.dto.CidadeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CIDADE")
public class Cidade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIDADE")
    @SequenceGenerator(name = "SEQ_CIDADE", sequenceName = "SEQ_CIDADE", allocationSize = 1)
    private Long id;

    @Column(name = "UF")
    private String uf;

    @Column(name = "NOME")
    private String nome;

    public Cidade(Long id) {
        this.id = id;
    }

    public static Cidade of(CidadeRequest request) {
        return Cidade
                .builder()
                .uf(request.getUf())
                .nome(request.getNome())
                .build();
    }
}
