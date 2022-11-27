package br.edu.fatecfranca.basketballapi.repository;

import br.edu.fatecfranca.basketballapi.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
