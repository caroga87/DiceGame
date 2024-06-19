package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional <User> findUserByUsername (String username);
}
