package br.edu.fatecfranca.basketballapi.model;

import br.edu.fatecfranca.basketballapi.dto.JogadorRequest;
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
@Table(name = "JOGADOR")
public class Jogador {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JOGADOR")
    @SequenceGenerator(name = "SEQ_JOGADOR", sequenceName = "SEQ_JOGADOR", allocationSize = 1)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_EQUIPE", foreignKey = @ForeignKey(name = "FK_EQUIPE"), referencedColumnName = "id")
    private Equipe equipe;

    public static Jogador of(JogadorRequest request) {
        return Jogador
                .builder()
                .nome(request.getNome())
                .build();
    }
}
