package br.edu.fatecfranca.basketballapi.repository;

import br.edu.fatecfranca.basketballapi.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
