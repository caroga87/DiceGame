package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerById(Integer id);
    void updatePlayerName(Integer id, String newName);
    void deletePlayer(Integer id);
    double calculateSuccessPercentage(PlayerDTO playerDTO);
    double calculateAverageSuccessPercentage();
    List <PlayerDTO> playerRankingList();

}
