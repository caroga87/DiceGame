package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Game;
import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository <Game, Integer> {
    List<Game> findByPlayer (Player player);
    void deleteByPlayer (Player player);

}
