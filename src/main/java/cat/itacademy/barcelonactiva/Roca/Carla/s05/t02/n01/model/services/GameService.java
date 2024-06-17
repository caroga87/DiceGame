package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.GameDTO;

import java.util.List;

public interface GameService {

    GameDTO createGame(Integer id);
    List<GameDTO> getGamesById(Integer id);
    void deleteGameById (Integer id);

}
