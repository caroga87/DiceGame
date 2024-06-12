package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository <Game, Integer> {
}
