package br.edu.fatecfranca.basketballapi.repository;

import br.edu.fatecfranca.basketballapi.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
