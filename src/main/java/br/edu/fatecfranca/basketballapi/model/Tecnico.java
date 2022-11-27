package br.edu.fatecfranca.basketballapi.model;

import br.edu.fatecfranca.basketballapi.dto.TecnicoRequest;
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
@Table(name = "TECNICO")
public class Tecnico {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_TECNICO", sequenceName = "SEQ_TECNICO", allocationSize = 1)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_EQUIPE", foreignKey = @ForeignKey(name = "FK_EQUIPE"), referencedColumnName = "id")
    private Equipe equipe;

    public static Tecnico of(TecnicoRequest request) {
        return Tecnico
                .builder()
                .nome(request.getNome())
                .equipe(new Equipe(request.getEquipeId()))
                .build();
    }
}
