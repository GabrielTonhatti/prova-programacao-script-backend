package br.edu.fatecfranca.basketballapi.model;

import br.edu.fatecfranca.basketballapi.dto.JogoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JOGO")
public class Jogo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JOGO")
    @SequenceGenerator(name = "SEQ_JOGO", sequenceName = "SEQ_JOGO", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FK_EQUIPE_CASA", foreignKey = @ForeignKey(name = "FK_EQUIPE_CASA"), referencedColumnName = "id")
    private Equipe equipeCasa;

    @OneToOne
    @JoinColumn(name = "FK_EQUIPE_VISITANTE", foreignKey = @ForeignKey(name = "FK_EQUIPE_VISITANTE"), referencedColumnName = "id")
    private Equipe equipeVisitante;

    @Column(name = "PONTOS_CASA")
    private Integer pontosCasa;

    @Column(name = "PONTOS_VISITANTE")
    private Integer pontosVisitante;

    @Column(name = "DATA")
    private LocalDateTime data;

    public static Jogo of(JogoRequest request) {
        return Jogo
                .builder()
                .data(LocalDateTime.now())
                .pontosCasa(request.getPontosCasa())
                .pontosVisitante(request.getPontosVisitante())
                .equipeCasa(new Equipe(request.getEquipeCasaId()))
                .equipeVisitante(new Equipe(request.getEquipeVisitanteId()))
                .build();
    }
}
