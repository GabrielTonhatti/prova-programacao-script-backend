package br.edu.fatecfranca.basketballapi.model;

import br.edu.fatecfranca.basketballapi.dto.EquipeRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EQUIPE")
public class Equipe {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPE")
    @SequenceGenerator(name = "SEQ_EQUIPE", sequenceName = "SEQ_EQUIPE", allocationSize = 1)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "FK_CIDADE", foreignKey = @ForeignKey(name = "FK_CIDADE"), referencedColumnName = "id")
    private Cidade cidade;

    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private Set<Jogador> jogadores;

    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private Set<Tecnico> tecnicos;

    public Equipe(Long id) {
        this.id = id;
    }

    public static Equipe of(EquipeRequest request) {
        return Equipe
                .builder()
                .nome(request.getNome())
                .cidade(new Cidade(request.getCidadeId()))
                .build();
    }
}
