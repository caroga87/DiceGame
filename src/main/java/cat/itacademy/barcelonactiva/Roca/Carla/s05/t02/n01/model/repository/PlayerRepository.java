package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository <Player, Integer> {

    Optional <Player> findByUsernameIgnoreCase(String username);
    boolean existsByUsername (String username);


}
